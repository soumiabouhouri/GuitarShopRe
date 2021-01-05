package sample.logic;

import sample.DAO.guitarDao;
import sample.Model.Guitar;

import java.util.List;

public class GuitarService {
    guitarDao guitarDao = new guitarDao();

    public List<Guitar> getAllGuitars() {
        return guitarDao.getAllGuitars();
    }

    public void reduceStockAmount(int amount, Guitar guitar, boolean reduce) {
        guitarDao.reduceStockAmount(amount, guitar, reduce);
    }
}
