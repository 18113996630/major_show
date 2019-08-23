package com.hrong.major.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.User;
import com.hrong.major.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.hrong.major.constant.Constant.KEY;

/**
 * @Author hrong
 **/
@Component
public class JwtUtils {
	private static UserService service;
	@Resource
	private UserService userService;

	public static User getUserByToken(String token){
		String user = Jwts.parser()
				.setSigningKey(Base64Utils.encodeToString(KEY.getBytes()))
				.requireAudience("audience")
				.requireIssuer("issuer")
				.parseClaimsJws(token.replace("Bearer ", ""))
				.getBody()
				.getSubject();
		return service.getOne(new QueryWrapper<User>().eq("account", user));
	}

	public static User getUserByRequest(HttpServletRequest request){
		String token = CookieUtils.getToken(request);
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		String user = Jwts.parser()
				.setSigningKey(Base64Utils.encodeToString(KEY.getBytes()))
				.requireAudience("audience")
				.requireIssuer("issuer")
				.parseClaimsJws(token.replace("Bearer ", ""))
				.getBody()
				.getSubject();
		return service.getOne(new QueryWrapper<User>().eq("account", user));
	}

	public static String getTokenByAccount(String account){
		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(String.valueOf(account))
				.setAudience("audience")
				.setExpiration(getExpiration())
				.setIssuer("issuer")
				.signWith(SignatureAlgorithm.HS512, Base64Utils.encodeToString(KEY.getBytes()));
		String token = jwtBuilder.compact();
		return token;
	}

	private static Date getExpiration() {
		long time = 24 * 60 * 60 * 1000L;
		return new Date(System.currentTimeMillis() + time);
	}

	@PostConstruct
	public void init(){
		service = userService;
	}
}
