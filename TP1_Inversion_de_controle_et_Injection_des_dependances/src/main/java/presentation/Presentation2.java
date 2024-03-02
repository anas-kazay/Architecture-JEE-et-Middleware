package presentation;




import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("config.txt"));
            String daoClassName = scanner.nextLine();
            Class cDao = Class.forName(daoClassName);
            // cDao.newInstance() is deprecated since Java 9
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();
            // System.out.println(dao.getDate());
            String metierClassName = scanner.nextLine();
            Class cMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();
            Method method = cMetier.getMethod("setDao", IDao.class);
            method.invoke(metier, dao);
            System.out.println("Result : " +metier.calcul());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
