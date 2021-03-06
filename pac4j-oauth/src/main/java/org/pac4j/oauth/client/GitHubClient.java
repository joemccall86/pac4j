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

import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.OAuthAttributesDefinitions;
import org.pac4j.oauth.profile.github.GitHubProfile;
import org.scribe.builder.api.GitHubApi;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.oauth.ProxyOAuth20ServiceImpl;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class is the OAuth client to authenticate users in GitHub.
 * <p />
 * It returns a {@link org.pac4j.oauth.profile.github.GitHubProfile}.
 * <p />
 * More information at http://developer.github.com/v3/users/
 * 
 * @see org.pac4j.oauth.profile.github.GitHubProfile
 * @author Jerome Leleu
 * @since 1.0.0
 */
public class GitHubClient extends BaseOAuth20Client<GitHubProfile> {
    
    public GitHubClient() {
    }
    
    public GitHubClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }
    
    @Override
    protected GitHubClient newClient() {
        return new GitHubClient();
    }
    
    @Override
    protected void internalInit() {
        super.internalInit();
        this.service = new ProxyOAuth20ServiceImpl(new GitHubApi(),
                                                   new OAuthConfig(this.key, this.secret, this.callbackUrl,
                                                                   SignatureType.Header, "user", null),
                                                   this.connectTimeout, this.readTimeout, this.proxyHost,
                                                   this.proxyPort);
    }
    
    @Override
    protected String getProfileUrl() {
        return "https://api.github.com/user";
    }
    
    @Override
    protected GitHubProfile extractUserProfile(final String body) {
        final GitHubProfile profile = new GitHubProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            for (final String attribute : OAuthAttributesDefinitions.githubDefinition.getAllAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
        }
        return profile;
    }
}
