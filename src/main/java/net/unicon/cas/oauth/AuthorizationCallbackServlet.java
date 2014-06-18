/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package net.unicon.cas.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.pac4j.oauth.profile.casoauthwrapper.CasOAuthWrapperProfile;
import org.scribe.model.Token;

public class AuthorizationCallbackServlet extends AbstractOAuthServlet {

    private static final long serialVersionUID = -5013470069334912862L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            System.out.println(req.getQueryString());
            System.out.println(req.getRequestURI());
            
            WebContext ctx = new J2EContext(req, resp);
            OAuthCredentials credentials = client.getCredentials(ctx);
            System.out.println("Token: " + credentials.getVerifier());
            
            CasOAuthWrapperProfile profile = client.getUserProfile(credentials, ctx);
            System.out.println("User id: " + profile.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}