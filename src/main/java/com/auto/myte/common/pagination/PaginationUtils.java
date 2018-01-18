package com.auto.myte.common.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * 改ページの共通クラス
 * 
 * @author chunhui.li
 */
public class PaginationUtils {

    public static List<Pagination> pagination(int limit, int index, int disp) {

        int last = (int) Math.ceil(limit * 1.0 / disp);
        if (last < index)
            return PaginationUtils.pagination(1, 1, disp);

        int start = index - disp / 2 > 1 ? index - disp / 2 : 1;
        int end = start + disp - 1 > last ? last : start + disp - 1;
        start = end - disp + 1 > 1 ? end - disp + 1 : 1;

        List<Pagination> paginations = new ArrayList<Pagination>();

        if (index != 1)
            paginations.add(new Pagination(1, "", null));
        if (index > 1)
            paginations.add(new Pagination(index - 1, "&larr;", null));

        for (int i = start; i <= end; i++) {
            if (i == index) {
                paginations.add(new Pagination(i, Integer.toString(i), "active"));
            } else {
                paginations.add(new Pagination(i, Integer.toString(i), null));
            }
        }

        if (index < last)
            paginations.add(new Pagination(index + 1, "&rarr;", null));
        if (index != last)
            paginations.add(new Pagination(last, "&raquo;", null));

        return paginations;
    }
}
