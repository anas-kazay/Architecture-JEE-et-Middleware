package extension;




import dao.IDao;
import org.springframework.stereotype.Component;

import java.util.Date;


public class DaoImpl2 implements IDao {
    @Override
    public Date getDate() {
        System.out.println("Version Capteurs");
        return new Date();
    }
}
