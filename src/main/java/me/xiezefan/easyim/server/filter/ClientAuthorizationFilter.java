package me.xiezefan.easyim.server.filter;


import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.util.StringUtil;
import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Client Authorization Filter
 * Created by xiezefan-pc on 2015/4/2 0002.
 */
@Provider
public class ClientAuthorizationFilter implements ContainerRequestFilter {
    public static final List<Pattern> noFilterPattern;

    static {
        noFilterPattern = new ArrayList<Pattern>();
        noFilterPattern.add(Pattern.compile("^(users/login)"));
        noFilterPattern.add(Pattern.compile("^(users/register)"));
//        noFilterPattern.add(Pattern.compile("^(messages/upPicture)"));
//        noFilterPattern.add(Pattern.compile("^(messages/upload2IM)"));
    }

    @Autowired
    private UserDao userDao;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    	String path = containerRequestContext.getUriInfo().getPath();
        System.out.println("+++++++++++++++++++++++"+"\n");
        System.out.println("path:"+path+"\n");
        for (Pattern pattern : noFilterPattern) {
            if (pattern.matcher(path).find()) {
                return;
            }
        }

        String authCode = containerRequestContext.getHeaders().getFirst("Authorization");
//        System.out.println("authCode:"+authCode+"\n");
        if (StringUtil.isEmpty(authCode)) {
            authFail(containerRequestContext);
            return;
        }

        String[] tempArray = authCode.split(" ");
//        System.out.println("tempArray:"+tempArray[0]+"\n");
//        System.out.println("tempArray:"+tempArray[1]+"\n");
        if (tempArray.length != 2) {
            authFail(containerRequestContext);
            return;
        }

        authCode = new String(Base64.decode(tempArray[1].getBytes()));
//        System.out.println("authCode:"+authCode+"\n");
        tempArray = authCode.split(":");
        if (tempArray.length != 2) {
            authFail(containerRequestContext);
            return;
        }


        User user = userDao.findByUsername(tempArray[0]);
//        System.out.println("user.getPassword():"+user.getPassword()+"\n");
//        System.out.println("tempArray[1]:"+tempArray[1]+"\n");
        if (user == null || !user.getPassword().equals(tempArray[1])) {
            authFail(containerRequestContext);
            return;
        }
        System.out.println("+++++++++++++++++++++++"+"\n");
        containerRequestContext.getHeaders().add("User", user.getId());
    }

    private void authFail(ContainerRequestContext containerRequestContext) {

        containerRequestContext.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(ResponseBuilder.ERROR_AUTHORIZATION_FAIL)
                .build());
    }
}
