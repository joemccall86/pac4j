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
package org.pac4j.oauth.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.dropbox.DropBoxAttributesDefinition;
import org.pac4j.oauth.profile.facebook.FacebookAttributesDefinition;
import org.pac4j.oauth.profile.github.GitHubAttributesDefinition;
import org.pac4j.oauth.profile.google2.Google2AttributesDefinition;
import org.pac4j.oauth.profile.linkedin.LinkedInAttributesDefinition;
import org.pac4j.oauth.profile.twitter.TwitterAttributesDefinition;
import org.pac4j.oauth.profile.windowslive.WindowsLiveAttributesDefinition;
import org.pac4j.oauth.profile.wordpress.WordPressAttributesDefinition;
import org.pac4j.oauth.profile.yahoo.YahooAttributesDefinition;

/**
 * This class defines all the attributes definitions.
 * 
 * @author Jerome Leleu
 * @since 1.1.0
 */
public final class OAuthAttributesDefinitions {
    
    public final static AttributesDefinition facebookDefinition = new FacebookAttributesDefinition();
    
    public final static AttributesDefinition githubDefinition = new GitHubAttributesDefinition();
    
    public final static AttributesDefinition google2Definition = new Google2AttributesDefinition();
    
    public final static AttributesDefinition linkedinDefinition = new LinkedInAttributesDefinition();
    
    public final static AttributesDefinition twitterDefinition = new TwitterAttributesDefinition();
    
    public final static AttributesDefinition yahooDefinition = new YahooAttributesDefinition();
    
    public final static AttributesDefinition windowsLiveDefinition = new WindowsLiveAttributesDefinition();
    
    public final static AttributesDefinition wordPressDefinition = new WordPressAttributesDefinition();
    
    public final static AttributesDefinition dropBoxDefinition = new DropBoxAttributesDefinition();
}
