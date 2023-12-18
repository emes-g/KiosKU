import konkuk.swarchitecture.team6.DBConn;
import org.junit.Test;

import static org.junit.Assert.fail;

public class PaymentByCardTest {

    @Test(expected = Exception.class)
    public void 데이터베이스_연결_오류() throws Exception {
        DBConn.getConnection();

        fail("데이터베이스 연결 오류가 나야 합니다.");
    }

}
