import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.auto.myte.utils.CommonUtils;
import com.auto.myte.utils.ParseJsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParseJsonUtilsTest {

	@Test
	public void test3() {
		List<String> list = new ArrayList<String>();
		list.add("令頁料又言正");
		list.add("現・チ・ク・割引No.0722");
		list.add("日付'17年05月18日");
		list.add("東京タクシーヤンター");
		list.add("車番3213");
		list.add("運賃料金計");
		list.add("00");
		list.add("\\3770円");
		list.add("\\3770円");
		list.add("合計");
		list.add("\\3770円");
		String date = ParseJsonUtils.pareseText(list, CommonUtils.DATE_REGEX);
		assertEquals("17年05月18日", date);
		String price = ParseJsonUtils.pareseText(list, CommonUtils.PRICE_REGEX);
		assertEquals("3770円", price);
		String taxi = ParseJsonUtils.pareseText(list, CommonUtils.TAXI_REGEX);
		assertFalse(StringUtils.isEmpty(taxi));
	}

	@Test
	public void getMatchContent2() {
		List<String> list = new ArrayList<String>();
		list.add("¥600");
		list.add("¥75,000");
		list.add("¥16,580");
		list.add("\\780");
		String price = ParseJsonUtils.pareseTextByMeals(list, CommonUtils.PRICE_REGEX1);
		System.out.println(price);
	}

	@Test
	public void test4() {
		List<String> list = new ArrayList<String>();
		list.add("2016-10-06");
		list.add("Β-Τ3255");
		list.add("Β:14");
		list.add("8:33");
		list.add("25.6Θή");
		String date = ParseJsonUtils.parserCnAmount(list);
		System.out.println(date);
//		String price = ParseJsonUtils.pareseText(list, CommonUtils.PRICE_REGEX1);
//		System.out.println(price);
//		String taxi = ParseJsonUtils.pareseText(list, CommonUtils.TAXI_REGEX);
//		System.out.println(taxi);
	}
}
