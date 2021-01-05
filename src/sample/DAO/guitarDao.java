package sample.DAO;

import sample.Model.Guitar;

import java.util.List;

public class guitarDao {
    DataBase db = new DataBase();

    public List<Guitar> getAllGuitars() {
        return db.getGuitars();
    }

    public void reduceStockAmount(int amount, Guitar article, boolean reduce) {
        for (Guitar a : db.getGuitars()) {
            if (a.getGuitarID() == article.getGuitarID()) {
                if (reduce) { a.setQuantity(a.getQuantity() - amount); } else {
                    a.setQuantity(a.getQuantity() + amount);
                }

                break;
            }
        }
    }
}
