package presentation;


import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
         DaoImpl dao = new DaoImpl();
         MetierImpl metier = new MetierImpl(dao);
         metier.setDao(dao);
         System.out.println("Result : " + metier.calcul());

    }
}
