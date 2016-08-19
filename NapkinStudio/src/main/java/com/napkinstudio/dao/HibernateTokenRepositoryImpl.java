package com.napkinstudio.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napkinstudio.dao.AbstractDao;
import com.napkinstudio.entity.PersistentLogin;
import com.napkinstudio.entity.User;
import com.napkinstudio.util.FTPCommunicator;
import com.napkinstudio.dao.IPersistentLoginDao;
import java.io.IOException;
import org.springframework.transaction.annotation.Propagation;

//@Repository("tokenRepositoryDao")
//@Transactional
//public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
//		implements PersistentTokenRepository {



@Service
	public class HibernateTokenRepositoryImpl implements PersistentTokenRepository{

	@Autowired
	private IPersistentLoginDao IPersistentLoginDao;

	
    @Transactional
    public void save(PersistentLogin persistentLogin) {
    	IPersistentLoginDao.save(persistentLogin);
    }

    @Transactional
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
    	IPersistentLoginDao.save(persistentLogin);
	}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
    	PersistentLogin persistentLogin =IPersistentLoginDao.findOne(seriesId);		
		if (persistentLogin != null) {
			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());			
		}else{return null;}
	}

    
    
	@Override
	public void removeUserTokens(String username) {
    	PersistentLogin persistentLogin =IPersistentLoginDao.findByUserName(username);		
		if (persistentLogin != null) {
	    	IPersistentLoginDao.delete(persistentLogin);			
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
    	PersistentLogin persistentLogin =IPersistentLoginDao.findOne(seriesId);		
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
    	IPersistentLoginDao.save(persistentLogin);
	}

}
