package metier;


import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component("metier")
public class MetierImpl implements IMetier {
    // couplage faible
    @Autowired
    private IDao dao;
    public MetierImpl(IDao dao) {
       this.dao = dao;
    }

    public MetierImpl(){

    }
    @Override
    public Date calcul() {

        return dao.getDate();
    }
    // ingecter dans la variable dao un objet d'une classe qui implemente l'interface IDao
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}


