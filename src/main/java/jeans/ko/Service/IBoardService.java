package jeans.ko.Service;


import jeans.ko.Dto.BoardDto;




public interface IBoardService {

    public void insert(BoardDto boardDto) ;

    public int delete(int look_num) ;

}
