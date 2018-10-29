package com.quorum.tessera.thridparty;

import com.quorum.tessera.app.RestApp;
import com.quorum.tessera.config.appmarkers.ThirdPartyAPP;
import com.quorum.tessera.service.locator.ServiceLocator;

import javax.ws.rs.ApplicationPath;

/**
 * The third party API
 */
@ApplicationPath("/")
public class ThirdParty extends RestApp implements ThirdPartyAPP {

    public ThirdParty(final ServiceLocator serviceLocator, final String contextName) {
        super(serviceLocator, contextName);
    }
}
