package jeans.ko.Service;


import jeans.ko.Dto.BoardDto;




public interface IBoardService {

    public void insert(BoardDto boardDto) ;

    public void delete(int look_num) ;

}
