import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.auto.myte.utils.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonUtilsTest {

	@Test
	public void convertYyyyMmddTest() {
		String date = "17年05月18日";
    	String cdate = CommonUtils.convertYyyyMmdd(date);
    	System.out.println(cdate);

		String date1 = "2017年05月19日";
    	String cdate1 = CommonUtils.convertYyyyMmdd(date1);

		String date2 = "2017 年05 月19 日";
    	String cdate2 = CommonUtils.convertYyyyMmdd(date1);
    	System.out.println(cdate2);

    	
    	assertEquals("2017/05/18", cdate);
    	assertEquals("2017/05/19", cdate1);
    	assertEquals("2017/05/19", cdate2);
	}


}
