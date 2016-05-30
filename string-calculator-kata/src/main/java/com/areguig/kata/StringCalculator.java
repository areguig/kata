package com.areguig.kata;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akli on 29/05/16.
 */
public class StringCalculator {

	private static final List<String> REGEX_SPECIAL_CHARS =
			Arrays.asList(new String[]{"*", ".", "\\", "^", "$", "{", "}", "[", "]"});

	public static int add(String numbers) throws Exception {
		String[] numbersArray = {"0"};
		if (!StringUtils.isEmpty(numbers)) {
			String del = "";
			if (numbers.startsWith("//")) {
				String del_line = numbers.split("\n")[0];
				if (del_line.contains("][")) {
					String[] split = del_line.substring(2, del_line.length() - 1).split("]");
					del = Arrays.asList(split).stream().
							reduce((d1, d2) ->
									(REGEX_SPECIAL_CHARS.contains(d1.substring(1)) ? "\\" + d1.substring(1) : d1.substring(1))
											+ "|" +
											(REGEX_SPECIAL_CHARS.contains(d1.substring(1)) ? "\\" + d2.substring(1) : d2.substring(1))).get();
				} else {
					del = del_line.substring(2);
				}
			}
			numbersArray = StringUtils.isNoneBlank(del) ? numbers.split("\n")[1].split(del)
					: numbers.contains(",") ? numbers.split(",")
						: numbers.contains("\n") ? numbers.split("\n")
							: numbers.split(";");
		}
		int res = 0;
		String negatives = "";
		for (String n : numbersArray) {
			if (n.startsWith("-")) {
				negatives += n + ',';
			} else {
				res += (StringUtils.isEmpty(n) || Integer.parseInt(n) >= 1000) ? 0 : Integer.parseInt(n);
			}
		}
		if (StringUtils.isNoneBlank(negatives)) {
			throw new Exception("negatives not allowed " + negatives.substring(0, negatives.length() - 1));
		}

		return res;
	}
}
