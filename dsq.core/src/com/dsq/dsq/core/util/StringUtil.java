package com.dsq.dsq.core.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import coo.base.exception.BusinessException;
import coo.base.model.Page;
import coo.base.util.DateUtils;
import coo.core.model.SearchModel;

/**
 * @author ruilu.li
 * @version [NGC V1.1, 2015年11月24日]
 * @备注：
 */
public class StringUtil extends StringUtils{
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @author LRL
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * 
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @author LRL
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 根据分隔符转换为数组
	 * 
	 * @author LRL
	 * @param str
	 * @param delim
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String[] parseToArray(String str, String delim) {
		ArrayList arr = new ArrayList();
		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()) {
			arr.add(st.nextToken());
		}
		String[] ret = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			ret[i] = (String) arr.get(i);
		}
		return ret;
	}

	/**
	 * 字符串替換
	 * 
	 * @author LRL
	 * @param str
	 * @param old
	 * @param rep
	 * @return
	 * 
	 */
	public static String replace(String str, String old, String rep) {
		if ((str == null) || (old == null) || (rep == null)) {
			return "";
		}
		int index = str.indexOf(old);
		if ((index < 0) || old.equals("")) {
			return str;
		}
		StringBuffer strBuf = new StringBuffer(str);
		while (index >= 0) {
			strBuf.delete(index, index + old.length());
			strBuf.insert(index, rep);
			index = strBuf.toString().indexOf(old);
		}
		return strBuf.toString();
	}

	/**
	 * 字符串替换，只替换一次
	 * 
	 * @author LRL
	 * @param str
	 * @param old
	 * @param rep
	 * @return
	 * 
	 */
	public static String replaceOnlyOnce(String str, String old, String rep) {
		if ((old == null) || old.equals("")) {
			return str;
		}
		if ((str == null) || str.equals("")) {
			return str;
		}
		int leftIndex = str.indexOf(old);
		if (leftIndex < 0) {
			return str;
		}
		String leftStr = str.substring(0, leftIndex);
		String rightStr = str.substring(leftIndex + old.length());
		return leftStr + rep + rightStr;
	}

	/**
	 * 把null字符串转换为“”，不为null则去掉左右空格
	 * 
	 * @author LRL
	 * @param foo
	 * @return
	 * 
	 */
	public static final String makeString(String foo) {
		return (foo == null ? "" : foo.trim());
	}

	/**
	 * Validates that the supplied string is neither <code>null</code> nor the
	 * empty string.
	 * 
	 * @param foo
	 *            The text to check.
	 * @return Whether valid.
	 */
	public static final boolean isValid(String foo) {
		return (foo != null && foo.length() > 0);
	}

	/**
	 * Determine whether a (trimmed) string is empty
	 * 
	 * @param foo
	 *            The text to check.
	 * @return Whether empty.
	 */
	public static final boolean isEmpty(String foo) {
		return (foo == null || foo.trim().length() == 0);
	}

	/**
	 * Returns the output of printStackTrace as a String.
	 * 
	 * @param e
	 *            A Throwable.
	 * @return A String.
	 */
	public static final String stackTrace(Throwable e) {
		String foo = null;
		try {
			// And show the Error Screen.
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(buf, true));
			foo = buf.toString();
		} catch (Exception f) {
			// Do nothing.
		}
		return foo;
	}

	/**
	 * Returns the output of printStackTrace as a String.
	 * 
	 * @param e
	 *            A Throwable.
	 * @param addPre
	 *            a boolean to add HTML
	 * 
	 *            <pre>
	 * tags around the stacktrace
	 * @return A String.
	 */
	public static final String stackTrace(Throwable e, boolean addPre) {
		if (addPre) {
			return "<pre>" + stackTrace(e) + "</pre>";
		} else {
			return stackTrace(e);
		}
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @author LRL
	 * @param s1
	 * @param s2
	 * @return
	 * 
	 */
	public static boolean equals(String s1, String s2) {
		if (s1 == null) {
			return (s2 == null);
		} else if (s2 == null) {
			// s1 is not null
			return false;
		} else {
			return s1.equals(s2);
		}
	}

	public static final int PPKEY_CLASSNAME = 0;

	public static final int PPKEY_ID = 1;

	public static final int PPKEY_PROPERTY = 2;

	/**
	 * Takes a String of the form substring[substring]subtring and returns the 3
	 * substrings
	 * 
	 * @return a three element String array
	 */
	public static String[] parseObjectKey(String s) {
		String[] p = new String[3];
		StringTokenizer st = new StringTokenizer(s, "[]");
		int count = st.countTokens();
		if (count > 1) {
			p[0] = st.nextToken();
			p[1] = st.nextToken();
			if (count == 3) {
				p[2] = st.nextToken();
			}
		}
		return p;
	}

	/**
	 * Remove Underscores from a string and replaces first Letters with
	 * Capitals. foo_bar becomes FooBar
	 */
	public static String removeUnderScores(String data) {
		String temp = null;
		StringBuffer out = new StringBuffer();
		temp = data;

		StringTokenizer st = new StringTokenizer(temp, "_");
		while (st.hasMoreTokens()) {
			String element = (String) st.nextElement();
			out.append(firstLetterCaps(element));
		}
		return out.toString();
	}

	/**
	 * Makes the first letter caps and leaves the rest as is.
	 */
	public static String firstLetterCaps(String data) {
		StringBuffer sbuf = new StringBuffer(data.length());
		sbuf.append(data.substring(0, 1).toUpperCase()).append(
				data.substring(1));
		return sbuf.toString();
	}

	/**
	 * Splits the provided CSV text into a list.
	 * 
	 * @param text
	 *            The CSV list of values to split apart.
	 * @param separator
	 *            The separator character.
	 * @return The list of values.
	 */
	public static String[] split(String text, String separator) {
		StringTokenizer st = new StringTokenizer(text, separator);
		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens()) {
			values[pos++] = st.nextToken();
		}
		return values;
	}

	/**
	 * Joins the elements of the provided array into a single string containing
	 * a list of CSV elements.
	 * 
	 * @param list
	 *            The list of values to join together.
	 * @param separator
	 *            The separator character.
	 * @return The CSV text.
	 */
	public static String join(String[] list, String separator) {
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				csv.append(separator);
			}
			csv.append(list[i]);
		}
		return csv.toString();
	}

	/**
	 * Takes a block of text which might have long lines in it and wraps the
	 * long lines based on the supplied wrapColumn parameter. It was initially
	 * implemented for use by VelocityEmail. If there are tabs in inString, you
	 * are going to get results that are a bit strange, since tabs are a single
	 * character but are displayed as 4 or 8 spaces. Remove the tabs.
	 * 
	 * @param inString
	 *            Text which is in need of word-wrapping.
	 * @param newline
	 *            The characters that define a newline.
	 * @param wrapColumn
	 *            The column to wrap the words at.
	 * @return The text with all the long lines word-wrapped.
	 */
	public static String wrapText(String inString, String newline,
			int wrapColumn) {
		StringTokenizer lineTokenizer = new StringTokenizer(inString, newline,
				true);
		StringBuffer stringBuffer = new StringBuffer();

		while (lineTokenizer.hasMoreTokens()) {
			try {
				String nextLine = lineTokenizer.nextToken();

				if (nextLine.length() > wrapColumn) {
					// This line is long enough to be wrapped.
					nextLine = wrapLine(nextLine, newline, wrapColumn);
				}

				stringBuffer.append(nextLine);
			} catch (NoSuchElementException nsee) {
				// thrown by nextToken(), but I don't know why it would
				break;
			}
		}

		return (stringBuffer.toString());
	}

	/**
	 * Wraps a single line of text. Called by wrapText(). I can't think of any
	 * good reason for exposing this to the public, since wrapText should always
	 * be used AFAIK.
	 * 
	 * @param line
	 *            A line which is in need of word-wrapping.
	 * @param newline
	 *            The characters that define a newline.
	 * @param wrapColumn
	 *            The column to wrap the words at.
	 * @return A line with newlines inserted.
	 */
	protected static String wrapLine(String line, String newline, int wrapColumn) {
		StringBuffer wrappedLine = new StringBuffer();

		while (line.length() > wrapColumn) {
			int spaceToWrapAt = line.lastIndexOf(' ', wrapColumn);

			if (spaceToWrapAt >= 0) {
				wrappedLine.append(line.substring(0, spaceToWrapAt));
				wrappedLine.append(newline);
				line = line.substring(spaceToWrapAt + 1);
			} else {
				spaceToWrapAt = line.indexOf(' ', wrapColumn);

				if (spaceToWrapAt >= 0) {
					wrappedLine.append(line.substring(0, spaceToWrapAt));
					wrappedLine.append(newline);
					line = line.substring(spaceToWrapAt + 1);
				} else {
					wrappedLine.append(line);
					line = "";
				}
			}
		}

		wrappedLine.append(line);

		return wrappedLine.toString();
	}

	/**
	 * convert the ISO char encoding to GBK
	 * 
	 * @author LRL
	 * @param str
	 *            the ISO encoding string
	 * @return the GBK encoding string for recursive invoke of this function in
	 *         log.error()
	 */
	public static String ISOtoGBK(String str) {

		byte[] by = null;
		try {
			by = str.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return str;
		}
		try {
			String a = new String(by, "GBK");
			return a;
		} catch (UnsupportedEncodingException ex1) {
			ex1.printStackTrace();
			return str;
		}
	}

	/**
	 * 格式化IP
	 * 
	 * @author LRL
	 * @param sourceIp
	 * @return
	 * 
	 */
	public static String formatRuleIp(String sourceIp) {
		String objectIp = "";
		if (sourceIp != null && !sourceIp.equals("") && sourceIp.length() > 0) {
			String port = "";
			int i = 0;
			while (sourceIp.indexOf('.') != -1) {
				port = sourceIp.substring(0, sourceIp.indexOf('.'));
				sourceIp = sourceIp.substring(sourceIp.indexOf('.') + 1);
				if (!port.equals("") && port.length() > 0) {
					if (port.length() < 3) {
						port = "0" + port;
					}
					if (port.length() < 3) {
						port = "0" + port;
					}
				}
				objectIp += port + ".";
				if (sourceIp.indexOf('.') == -1) {
					port = sourceIp;
					if (port.length() < 3) {
						port = "0" + port;
					}
					if (port.length() < 3) {
						port = "0" + port;
					}
					objectIp += port;
				}
				i++;
				if (objectIp.length() == 16) {
					objectIp = objectIp.substring(0, objectIp.length() - 1);
				}
				if (i == 4) {
					break;
				}
			}
		}

		return objectIp;
	}

	/**
	 * convert the GB2312 char encoding to UTF-8
	 * 
	 * @author LRL
	 * @param str
	 *            the GB2312 encoding string
	 * @return the UTF-8 encoding string
	 */
	public static String GB2312toUTF(String str) {
		byte[] by = null;
		try {
			by = str.getBytes("GB2312");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return str;
		}

		try {
			String a = new String(by, "UTF-8");
			return a;
		} catch (UnsupportedEncodingException ex1) {
			ex1.printStackTrace();
			return str;
		}
	}

	/**
	 * convert the UTF-8 char encoding to GB2312
	 * 
	 * @author LRL
	 * @param str
	 *            the UTF-8 encoding string
	 * @return the GB2312 encoding string
	 */
	public static String UTFtoGB2312(String str) {
		byte[] by = null;
		try {
			by = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return str;
		}
		try {
			String a = new String(by, "GB2312");
			return a;
		} catch (UnsupportedEncodingException ex1) {
			ex1.printStackTrace();
			return str;
		}
	}

	/**
	 * trim the string even when it's null
	 * 
	 * @author LRL
	 * @param str
	 *            the string need to be trimmed
	 * @return the trimmed string
	 */
	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 判断str是否在strArr中
	 * 
	 * @author LRL
	 * @param str
	 * @param strArr
	 * @return true:str在strArr中出现,;false otherwise.
	 */
	public static boolean stringInArray(String str, String[] strArr) {
		for (int i = 0; i < strArr.length; i++) {
			if (str.equals(strArr[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将带有格式的字符串转换成HTML方式
	 * 
	 * @author LRL
	 * @param origine
	 * @return
	 */
	public static String convert2Html(String origine) {
		String outStr = null;
		if (origine != null) {
			String tmp = StringUtil.replace(origine, ">", "&gt;");
			String tmp2 = StringUtil.replace(tmp, "<", "&lt;");
			String tmp3 = StringUtil.replace(tmp2, " ", "&nbsp;");

			String tmp4 = StringUtil.replace(tmp3, "\r\n", "<br>");

			outStr = tmp4;
		} else {
			outStr = "";
		}
		return outStr;
	}

	/**
	 * 去掉字符串中的空格
	 * 
	 * @author LRL
	 * @param str
	 * @return
	 * 
	 */
	public static String getTrimStr(String str) {
		if (null != str) {
			char[] ch = str.toCharArray();
			StringBuffer sb = new StringBuffer(ch.length);
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] != ' ') {
					sb.append(ch[i]);
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 校验密码合法性
	 * 
	 * @author LRL
	 * @rule 密码只能由“0-9”、“a-z”、“A-Z”、“!”、“?”、“_”组成
	 * @param pwd
	 * @return true 合法；false 不合法
	 */
	public static boolean checkPwd(String pwd) {
		if (pwd != null && !"".equals(pwd.trim())) {
			char[] charArray = pwd.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				char c = charArray[i];
				if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
						|| (c >= 'A' && c <= 'Z') || (c == '!') || (c == '?') || (c == '_'))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * ELF哈希码算法
	 * 
	 * @author LRL
	 * @param original
	 * @return
	 */
	public static int getFNVHashCode(String str) {
		int hash = 0;
		int x = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = (hash << 4) + str.charAt(i);
			if ((x = (int) (hash & 0xF0000000L)) != 0) {
				hash ^= (x >> 24);
				hash &= ~x;
			}
		}
		return (hash & 0x7FFFFFFF);
	}

	/**
	 * 获取一个字符串的MD5加密串
	 * 
	 * @author LRL
	 * @param sourceStr
	 * @return
	 */
	public static String getMD5Str(String sourceStr) {
		if (StringUtil.isEmpty(sourceStr)) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(sourceStr.getBytes());
			byte[] encodedToken = md.digest();
			for (int i = 0; i < encodedToken.length; i++) {
				if ((encodedToken[i] & 0xff) < 0x10) {
					buf.append("0");
				}
				buf.append(Long.toString(encodedToken[i] & 0xff, 16));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 判断字符串中是否含有中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainsChinese(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flag = false;
		if (matcher.find()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 计算查询时间区间
	 * 
	 * @author LRL
	 * @param column
	 * @param startDate
	 * @param endDate
	 * @param criteria
	 * @return
	 * @time 上午9:59:16
	 */
	public static Criteria calcTime(String column, Date startDate,
			Date endDate, Criteria criteria) {
		if (startDate != null && endDate != null && startDate.after(endDate)) {
			throw new BusinessException("查询起始日期不能大于截止日期。");
		}
		if (startDate != null) {
			criteria.add(Restrictions.ge(column, startDate));
		}

		if (endDate != null) {
			criteria.add(Restrictions.lt(column, DateUtils.getNextDay(endDate)));
		}

		return criteria;
	}
	
	/**
	 * 计算带是时分秒的Criteria区间
	 * 
	 * @author ruilu.li
	 * @param column
	 * @param startDate
	 * @param endDate
	 * @param criteria
	 * @return 
	 * @time 下午5:50:33
	 */
	public static Criteria calcCurrentTime(String column, Date startDate,
			Date endDate, Criteria criteria) {
		if (startDate != null && endDate != null && startDate.after(endDate)) {
			throw new BusinessException("查询起始日期不能大于截止日期。");
		}
		if (startDate != null) {
			criteria.add(Restrictions.ge(column, startDate));
		}

		if (endDate != null) {
			criteria.add(Restrictions.lt(column, DateUtil.addDay(endDate, 1)));
		}

		return criteria;
	}

	@SuppressWarnings("unchecked")
	public static Page calcPage(Criteria criteria, SearchModel searchModel) {
		criteria.setFirstResult((searchModel.getPageNo() - 1)
				* searchModel.getPageSize());
		criteria.setMaxResults(searchModel.getPageSize());
		Page<T> page = new Page<T>(criteria.list().size(),
				searchModel.getPageNo(), searchModel.getPageSize());
		page.setContents(criteria.list());
		return page;
	}

	/**
	 * win下获取机器ip
	 * 
	 * @author LRL
	 * @return
	 * @time 下午12:07:00
	 */
	private static String getWindows() {
		String winip = "winip";
		try {
			InetAddress ia = InetAddress.getLocalHost();
			winip = ia.getHostAddress().toString();
		} catch (UnknownHostException e) {
		}
		return winip;
	}

	/**
	 * linux 下获取机器ip
	 * 
	 * @author LRL
	 * @return
	 * @time 下午12:07:18
	 */
	private static String getLinux() {
		String linuxip = "linuxip";
		InetAddress ip = null;
		try {
			boolean findFlag = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (findFlag) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				// 遍历所有ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					// 127.开头的都是lookback地址
					if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						findFlag = true;
						break;
					}
				}
			}
			if (null != ip) {
				linuxip = ip.getHostAddress();
			}
		} catch (Exception e) {
		}
		return linuxip;
	}

	/**
	 * 获取运行环境的IP，
	 * 
	 * @备注 如果异常则用999代替//作废
	 * 
	 * @修改// 用整个机器IP表示，其中.用-替换
	 * 
	 * @author LRL
	 * @return
	 * @time 下午12:07:31
	 */
	public static String getLocalIpStr() {
		String ip;
		try {
			if (System.getProperties().getProperty("os.name").toLowerCase()
					.indexOf("win") >= 0) {
				ip = getWindows();
			} else {
				ip = getLinux();
			}

			if (!StringUtils.isEmpty(ip)) {
				ip = ip.replace(".", "-");
			}
		} catch (Exception e) {
			ip = "999";
		}
		return ip;
	}
}