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
}
