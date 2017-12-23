import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignInTest extends FSBase{

    @Test
    public void testSignIn() throws InterruptedException {
        fsLogin();
    }

    @Test
    public void testSignInAfterProductSearch() throws InterruptedException, IOException {
        fsSearch();
    }

    // Automated one more test case
    @Test
    public void testSignInAfterProductSearch2() throws InterruptedException, IOException {
        fsSearch();
    }

    // Changes from Shamsu

}
