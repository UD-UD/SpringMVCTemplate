package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.PersistentLogin;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by ud on 28/4/17.
 */
@Repository("tokenRepositoryDao")
@Transactional
public class TokenRepositoryIMPL extends AbstractDao<String,PersistentLogin> implements PersistentTokenRepository {
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);
    }

    @Override
    public void updateToken(String s, String tokenValue, Date date) {
        PersistentLogin persistentLogin = getByKey(s);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(date);
        update(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", s));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }
    }
}
