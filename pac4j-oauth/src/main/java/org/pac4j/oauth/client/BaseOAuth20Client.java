/*
  Copyright 2012 - 2013 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.exception.OAuthCredentialsException;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.pac4j.oauth.profile.OAuthProfile;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.utils.OAuthEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is the base implementation for client supporting OAuth protocol version 2.0.
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
public abstract class BaseOAuth20Client<U extends OAuthProfile> extends BaseOAuthClient<U> {
    
    protected static final Logger logger = LoggerFactory.getLogger(BaseOAuth20Client.class);
    
    public static final String OAUTH_CODE = "code";
    
    @Override
    protected String retrieveAuthorizationUrl(final WebContext context) {
        // no request token for OAuth 2.0 -> no need to save it in the context
        final String authorizationUrl = this.service.getAuthorizationUrl(null);
        logger.debug("authorizationUrl : {}", authorizationUrl);
        return authorizationUrl;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected OAuthCredentials getOAuthCredentials(final WebContext context) {
        final String verifierParameter = context.getRequestParameter(OAUTH_CODE);
        if (verifierParameter != null) {
            final String verifier = OAuthEncoder.decode(verifierParameter);
            logger.debug("verifier : {}", verifier);
            return new OAuthCredentials(verifier, getName());
        } else {
            final String message = "No credential found";
            logger.error(message);
            throw new OAuthCredentialsException(message);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Token getAccessToken(final OAuthCredentials credentials) {
        // no request token saved in context and no token (OAuth v2.0)
        final String verifier = credentials.getVerifier();
        logger.debug("verifier : {}", verifier);
        final Verifier clientVerifier = new Verifier(verifier);
        final Token accessToken = this.service.getAccessToken(null, clientVerifier);
        logger.debug("accessToken : {}", accessToken);
        return accessToken;
    }
    
    @Override
    protected boolean isDirectRedirection() {
        return true;
    }
}
