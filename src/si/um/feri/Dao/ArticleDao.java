package si.um.feri.Dao;

import si.um.feri.models.Artikel;

public interface ArticleDao extends DaoCrud<Artikel> {
    Artikel getByBarcode(String code);
}
