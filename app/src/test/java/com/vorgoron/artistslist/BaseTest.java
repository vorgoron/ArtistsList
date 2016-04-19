package com.vorgoron.artistslist;

import com.vorgoron.artistslist.di.component.TestApplicationComponent;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, application = TestApplication.class)
@Ignore
public abstract class BaseTest {

    protected TestApplicationComponent getTestComponent() {
        return (TestApplicationComponent) TestApplication.getApplicationComponent();
    }

}
