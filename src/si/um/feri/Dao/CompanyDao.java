package si.um.feri.Dao;
import si.um.feri.models.Podjetje;

public interface CompanyDao extends DaoCrud<Podjetje> {
    Podjetje getByName(String name);
}
