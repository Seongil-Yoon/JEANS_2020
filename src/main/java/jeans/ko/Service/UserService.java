package jeans.ko.Service;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.apache.jasper.compiler.JspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDto userDto;

    @Autowired
    private FileService fileService;

    @Autowired
    private IUtilService utilService;

    @Value("${directory}")
    String directory;

    //유저 개인 폴더 밑에 유저의 기본프로필사진&썸네일사진들을 저장하는 파일의 이름이다.
    @Value("${profilepath}")
    String profile;

    //작은 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileSmallheader}")
    String smallHeader;

    //중간 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileMiddleheader}")
    String middleHeader;

    //위도우에서는 \\, 리눅스서버에서는/
    @Value("${route}")
    String route;

    public int joinUser(UserDto userDto) {
        logger.info("joinUser메소드");
        userDto.setRole(1);
        System.out.println("nuserService에서");
        System.out.println("userDto.getUserid() = " + userDto.getUserid());
        System.out.println("userDto.getEmail() = " + userDto.getEmail());
        System.out.println("userDto.getNickname = " + userDto.getNickname());
        System.out.println("userDto.getPassword() = " + userDto.getPassword());
        System.out.println("userDto.getPicture() = " + userDto.getPicture());
        System.out.println("userDto.getHeight() = " + userDto.getHeight());
        System.out.println("userDto.getWeight() = " + userDto.getWeight());
        System.out.println("userDto.getRole() = " + userDto.getRole());
        System.out.println("userDto.getSex() = " + userDto.getSex());
        System.out.println("userDto = " + userDto);

        ArrayList<String>profilethumbnailPath= utilService.getProfilepath(userDto.getUserid());
        fileService.mkDir(profilethumbnailPath);

        int insert_count = userDao.insertUser(userDto);
        //dao에서 db에 넣는게 성공했는지 안했는지 확인
        //이게 userDao 가 성공했는지 안했는지 확인하고 if 문으로 갈라진다
        return insert_count;
    }

    public UserDto userLogin(UserDto userDto) {
        logger.info("userLogin메소드");
        UserDto successLogin = null;

        String id = userDto.getUserid();
        String password = userDto.getPassword();

        successLogin = userDao.getInformation(userDto);

        if (successLogin != null) {
            return successLogin;
        } else {
            return successLogin;
        }
    }

    public String getPicture(String userid) {
        logger.info("getPicture메소드");
        String picture = userDao.getPicture(userid);
        return picture;
    }

    //유저의 profile 폴더 내 썸네일 전체제거. 그후 유저테이블의 picture값을 공백으로
    @Override
    public void deleteProfilethumbnail(String userid) {
        /*
        path리스트는 구분자를 제외한 프로필사진 폴더까지의 경로모음 리스트다.
        첫번째로 기본경로까지, 두번째로 해당사용자의 아이디 마지막으로 profile폴더.
         */
        List<String> path =  utilService.getProfilepath(userid);
      //  path = utilService.getProfilepath(userid);

        System.out.println("path = " + path);
        //유저의 현재 사진 picture명.
        String picture = userDao.getPicture(userid);

        /*
        pictures리스트는 프로필폴더안에 사용자의 썸네일이미지다.
        원본, profileSmallheader+원본, profileMiddleheader원본 총 세개가 있다.
         */
        List<String> pictures = new ArrayList<String>();
        pictures.add(smallHeader + picture);
        pictures.add(middleHeader + picture);
        pictures.add(picture);


        //첫번째 매개변수는 경로, 두번째 매개변수는 삭제할 파일명들이 담겨있다.
        fileService.deleteFiles(path, pictures);

        //2. DB의 사진이름을 공백으로 지정
        userDao.changeprofileThumbnail(userid, "");
    }

    //유저의 profile 폴더 내 썸네일 이미지 업로드. 그 후 유저테이블 picture값을 파일명으로
    @Override
    public void setProfilethumbnail(String userid, MultipartFile file) throws Exception {
        /*
        path리스트는 구분자를 제외한 프로필사진 폴더까지의 경로모음 리스트다.
        첫번째로 기본경로까지, 두번째로 해당사용자의 아이디 마지막으로 profile폴더.
         */
        List<String> path = utilService.getProfilepath(userid);

        //프로필썸네일 용으로 사용할 파일을 추가한다.
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);

        //썸네일 사진을 해당 path에 업로드한다.
        fileService.uploadFiles(path, files);

        //프로필썸네일 생성용
        fileService.makeprofileThumbnail(path, file);

        //유저의 네임을 변경
        userDao.changeprofileThumbnail(userid, file.getOriginalFilename());

    }

}
