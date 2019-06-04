package si.um.feri.Dao;

import si.um.feri.models.InternalArticle;

public interface InternalArticleDao extends DaoCrud<InternalArticle> {
    InternalArticle getByName(String name);
}
