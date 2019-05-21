package org.frame.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import org.frame.util.PropertiesUtil;
import org.frame.bean.SessionBean;
import org.frame.util.*;
import org.frame.bean.LetterOrder;
import org.frame.exception.BusinessException;
import org.frame.util.RedisUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 作者： liwei
 * 时间：2018年1月2日
 * 描述：公共方法类
 */
public class Common {

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：JSON 转 对象
     *
     * @param pojo
     * @param tclass
     * @return
     */
    public static <T> T jsonToEntity(String pojo, Class<T> tclass) {
        try {
            return com.alibaba.fastjson.JSONObject.parseObject(pojo, tclass);
        } catch (Exception e) {
            System.out.println(tclass + "转 JSON 失败");
        }
        return null;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：json 转 List对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        if (Common.isNull(jsonString)) {
            jsonString = "1";// 当为空时加一个默认值 让最终list集合的size为0
        }
        List<T> ts = new ArrayList<T>();
        try {
            ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        } catch (Exception e) {
            System.out.println("json格式错误");
        }
        return ts;
    }


    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：获取服务器地址 例如：192.168.0.105
     *
     * @param request
     * @return
     */
    public static String locahost_url(HttpServletRequest request) {
        return request.getServerName();
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：获取项目路径
     *
     * @param request
     * @return
     */
    public static String ProjectPath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "/";
        return basePath;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：当前tomcat路径
     *
     * @param request
     * @return
     */
    public static String tomcat_url(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("");
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：生成 base64编码
     *
     * @param code
     * @return
     */
    public static String string_base64(String code) {
        return Common.encode(Base64.encodeBase64(code.getBytes()));
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     */
    public static String encode(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：密码MD5加密
     *
     * @param password
     * @return
     */
    public static String personEncrypt(String password) {
        String temp = EncryptUtils.encodeMD5String(password.concat(password
                .concat(EncryptUtils.encodeMD5String(password))));

        return EncryptUtils
                .encodeMD5String(EncryptUtils.encodeMD5String(temp
                        .substring(
                                0,
                                temp.length() > 6 ? (temp.length() - 6) : temp
                                        .length()).concat(
                                temp.substring(0, temp.length() / 2))));
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：string转map
     *
     * @param str
     * @return
     */
    public static Map<String, Object> stringToMap(String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = JSONObject.fromObject(jsonObject);
        return result;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月29日
     * 描述：string转List<Map>
     *
     * @param str
     * @return
     */
    public static List<Map> stringToListMap(String str) {
        List<Map> list = JSONArray.parseArray(str, Map.class);
        return list;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：java实体类转换为map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> BeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：截取小数点后两位double
     *
     * @param object
     * @return
     */
    public static double doubleIntercept(double object) {
        String number = String.format("%.2f", object);
        return Double.parseDouble(number);
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：截取小数点后两位string
     *
     * @param object
     * @return
     */
    public static String stringIntercept(double object) {
        String number = String.format("%.2f", object);
        return number;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：精确到毫秒数产生时间数
     *
     * @return
     */
    public static String numberOnlyRoomId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        String str = format.format(calendar.getTime());
        return str;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述： 年月日时间数
     *
     * @return
     */
    public static String numberDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String str = format.format(calendar.getTime());
        return str;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：九位随机不重复数
     *
     * @return
     */
    public static String randomDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMssSSS");
        Calendar calendar = Calendar.getInstance();
        String str = format.format(calendar.getTime());
        return str;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：判定是否为空
     *
     * @param o
     * @return
     */
    public static boolean isNull(Object o) {
        if (o == null || o.equals("") || o == "" || o == "null"
                || o.equals("null") || o.equals("undefined")) {
            return true;
        }
        if (o != null) {
            if (o.toString().replaceAll("\\s*", "").length() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：判定是否相等
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isEqual(Object o1, Object o2) {
        if (!Common.isNull(o1) && !Common.isNull(o2)) {
            if (o1 == o2 || o1.equals(o2)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：判断一个元素是否存在数组中
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean isExist(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：判断字符是否是数字
     *
     * @param string
     * @return
     */
    public static boolean isInt(String string) {
        return string.matches("\\d+");
    }

    /**
     * 作者： liwei
     * 时间：2017年12月22日
     * 描述：一个字符串是否包含另外一个字符串
     *
     * @param con  被判断对象   例：123456
     * @param bcon 判断对象    例：123
     * @return
     */
    public static boolean isContains(String con, String bcon) {
        return con.contains(bcon);
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：随机数，数量自定义
     *
     * @param o
     * @return
     */
    public static Object randomNumber(Integer o) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < o; i++) {
            buffer.append(random.nextInt(9) % (9) + 1);
        }
        return buffer;
    }

    /**
     * 作者： liwei
     * 时间：2017年12月18日
     * 描 述：把客户端对象值赋值给服务器端对象(不为空的值)---源数据在前，需要的数据在后，attributes为字段数组
     *
     * @param serverObject
     * @param clientObject
     * @param attributes
     * @return
     * @throws Exception
     */
    public static Object entityMerge(Object serverObject, Object clientObject,
                                     String[] attributes) throws Exception {
        Field s_field = null, c_field = null;
        Field[] s_fields = serverObject.getClass().getDeclaredFields();
        Field[] c_fields = clientObject.getClass().getDeclaredFields();
        Map<String, Field> s_map = new HashMap<String, Field>();
        Map<String, Field> c_map = new HashMap<String, Field>();
        for (Field field_s : s_fields) {
            s_map.put(field_s.getName(), field_s);
        }
        for (Field field_c : c_fields) {
            c_map.put(field_c.getName(), field_c);
        }
        // 属性名
        for (String string : attributes) {
            if (!Common.isNull(s_map.get(string))) {
                s_field = s_map.get(string);
                c_field = c_map.get(string);
                s_field.setAccessible(true);
                c_field.setAccessible(true);
                if (c_field.get(clientObject) != null
                        && !"".equals(c_field.get(clientObject))) {
                    s_field.set(serverObject, c_field.get(clientObject));
                }
            }

        }
        return serverObject;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月12日
     * 描述：相同属性的实体类进行赋值
     *
     * @param t1 空对象
     * @param t2 有数据的对象
     * @return
     */
    public static <T> T entityToEntity(T t1, T t2) {
        try {
            //防止Date为空值抛出异常
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            //进行赋值
            BeanUtils.copyProperties(t1, t2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t1;
    }

    /**
     * 作者： liwei
     * 时间：2017年11月22日
     * 描 述： html代码转word
     *
     * @param body html代码内容
     * @param url  word文档保存地址
     * @throws Exception
     */
    public static void htmlToWord(String body, String url) throws Exception {
        new HtmlToWord().htmlToWord2(body, url);
    }

    /**
     * 作者： liwei
     * 时间：2017年11月29日
     * 描 述：list参数打包成string(用于sql中的in)
     *
     * @param lists
     * @return
     */
    public static String listToString(List<String> lists) {
        StringBuffer buffer = new StringBuffer();
        for (String string : lists) {
            if (Common.isNull(buffer)) {
                buffer.append("'" + string + "'");
            } else {
                buffer.append(",").append("'" + string + "'");
            }
        }
        return buffer.toString();
    }

    /**
     * 作者： liwei
     * 时间：2017年12月7日
     * 描 述：删除list<String>里面重复的值
     *
     * @param list
     * @return
     */
    public static List<String> removeListDuplicate(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }


    /**
     * 作者： liwei
     * 时间：2017年12月26日
     * 描述：银行卡验证(采用爬虫爬取支付宝合作银行数据)
     *
     * @param bankId
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bankJudge(String bankId) {
        try {
            if (Common.isNull(bankId)) {
                throw new BusinessException("common.banIdIsNull");
            }
            Boolean bankStatus = BankCheck.checkBankCard(bankId);
            if (bankStatus == false) {
                throw new BusinessException("common.banIdFileterIsFalse");
            }
            //获取元素全名称
            String bankName = BankCheck.getNameOfBank(bankId);
            //获取银行新名称
            String bankNewName = bankName.substring(0, bankName.indexOf("·"));
            //获取卡类型
            String cardName = bankName.substring(bankName.indexOf("·") + 1, bankName.length());
            //1.获取阿里银行合作伙伴页面
            Document doc = Jsoup.connect("http://ab.alipay.com/i/yinhang.htm").get();
            Element body = doc.body();
            //2.获取对应数据的标签
            Elements es = body.select("li#ap-a-cnt-bank>ul>li>a>span");
            //3.遍历标签中的数据
            for (Element e : es) {
                String name = e.attr("title");//银行卡名称
                String eName = e.attr("class");//银行代码
                //银行icon地址 https://apimg.alipay.com/combo.png?d=cashier&t=银行代码
                if (eName != null && eName != "") eName = eName.substring(eName.indexOf(" ") + 1);
                //根据名称与爬到的支付宝合作银行来取得全名称以及银行代码
                if (Common.isContains(name, bankNewName)) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("bankName", name);
                    map.put("bankCode", eName);
                    map.put("headImg", "https://apimg.alipay.com/combo.png?d=cashier&t=" + eName);
                    map.put("icon", "http://www.liaoin.com:8080/banklogo/" + eName + ".png");
                    map.put("cardName", cardName);
                    return map;
                }
            }
        } catch (Exception e) {
            throw new BusinessException("rest.fail");
        }
        throw new BusinessException("common.banIdToDataIsNull");
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018年1月2日
     * 描述： Excel数据导出
     * @param title   标题（map中的key为对应数据源的key，value为表头名称）
     * @param headers 表头
     * @param beans   数据源(javaBean)
     **/
    public static <T> void exportExcelToBean(String title, List<Map<String, String>> headers, List<T> beans) {
        try {
            SpringUtil.getResponse().setHeader("Content-Disposition", "attachment;filename="
                    + new String(title.getBytes(), "iso-8859-1") + ".xls");
            ExcelUtil.exportExcelToBean(title, headers, beans, SpringUtil.getResponse().getOutputStream(), "yyyy-MM-dd HH:mm:ss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018年1月2日
     * 描述： Excel数据导出
     * @param title   标题
     * @param headers 表头（map中的key为对应数据源的key，value为表头名称）
     * @param beans   数据源(Map)
     **/
    public static void exportExcelToMap(String title, List<Map<String, String>> headers, List<Map> beans) {
        try {
            SpringUtil.getResponse().setHeader("Content-Disposition", "attachment;filename="
                    + new String(title.getBytes(), "iso-8859-1") + ".xls");
            ExcelUtil.exportExcelToMap(title, headers, beans, SpringUtil.getResponse().getOutputStream(), "yyyy-MM-dd HH:mm:ss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static Double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        return Point.getDistance(lat1, lng1, lat2, lng2);
    }


    /**
     * 作者： liwei
     * 时间：2018年2月1日
     * 描述：根据集合长度来判断数组当中是否有相同的值
     *
     * @param array
     * @return true:沒有  false:存在相同的值
     */
    public static boolean isCheakIsRepeat(List<String> strs) {
        if (strs.size() == 1) {
            return false;
        }
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < strs.size(); i++) {
            hashSet.add(strs.get(i));
        }
        if (hashSet.size() == strs.size()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 作者： liwei
     * 时间：2018年2月27日
     * 描 述：集合根据英文首字母排序并得到首字母
     *
     * @param lists
     * @return
     */
    public static List<LetterOrder> letterOrder(List<String> lists) {
        Comparator<Object> comparator = Collator
                .getInstance(java.util.Locale.CHINA);
        Collections.sort(lists, comparator);
        List<LetterOrder> le = new ArrayList<LetterOrder>();
        List<String> ls = new ArrayList<String>();
        char a = 'A';
        for (int i = 0; i < 26; i++) {
            ls.add(String.valueOf(a));
            a++;
        }
        List<String> str = Common.removeListDuplicate(ls);
        for (String string : str) {
            LetterOrder let = new LetterOrder();
            for (String s2 : lists) {
                String pyorder = LetterOrder.getPYIndexStr(s2, true);
                String letter = pyorder.substring(0, 1);
                if (Common.isEqual(string, letter)) {
                    let.getData().add(s2);
                } else {
                    continue;
                }
            }
            if (let.getData().size() != 0) {
                let.setLetter(string);
                le.add(let);
            }
        }
        return le;
    }

    /**
     * 作者： liwei
     * 时间：2018年3月7日
     * 描述：取指定区间值，随机数
     *
     * @param mix
     * @param max
     * @return
     */
    public static Double sectionValue(Integer min, Integer max) {
        Random random = new Random();
        //nextInt为产生一个0-max的区间数  用最大值减去最小值生成随机数并加上最小值就位区间值
        Integer price = random.nextInt(max - min) + min;
        Double oldPrice = Common.doubleIntercept(price + random.nextDouble());
        return oldPrice;
    }

    /**
     * 作者： liwei
     * 时间：2018年4月4日
     * 描述：获取实体里面所有的属性
     *
     * @param t
     * @return
     */
    public static <T> String[] entityClass(T tm, String[] noFz) {
        Class studentClass = tm.getClass(); // studentClass
        Field[] field = studentClass.getDeclaredFields();
        String[] str = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            if (!isExist(noFz, field[i].getName())) {
                str[i] = field[i].getName();
            }
        }
        return str;
    }

    /**
     * 作者： liwei
     * 时间：2018年4月28日
     * 描述：读取excel中的内容
     *
     * @param filePath 文件路径
     * @param columns  读取字段
     * @return
     */
    public static List<Map<String, Object>> readExclVal(String filePath, String[] columns) {
        return ReadExclValue.readValue(filePath, columns);
    }


    /**
     * 作者： liwei
     * 时间：2018年6月13日
     * 描述：数据打包成树形结构，数据包必须包含 id、parentId、children字段，children为List类型
     *
     * @param tree
     * @return
     * @throws Exception
     */
    public static List dataTofactorTree(List tree) throws Exception {
        if (tree != null) {
            List t_list = new ArrayList();
            Map map = new HashMap();
            for (Object o : tree) {
                Class clazz = o.getClass();
                Field id = clazz.getDeclaredField("id");
                if (!id.isAccessible()) {
                    id.setAccessible(true);
                }
                map.put(id.get(o), o);
            }
            for (Object o : map.keySet()) {
                Object obj = map.get(o);
                Class clazz = obj.getClass();
                Field pId = clazz.getDeclaredField("parentId");
                if (!pId.isAccessible()) {
                    pId.setAccessible(true);
                }
                Object id = pId.get(obj);
                if (id == null) {
                    t_list.add(obj);
                } else {
                    Object object = map.get(id);
                    //避免出现脏数据而抛出空指针异常
                    if (!Common.isNull(object)) {
                        Class clazz1 = object.getClass();
                        Field children = clazz1.getDeclaredField("children");
                        if (!children.isAccessible()) {
                            children.setAccessible(true);
                        }
                        List list = (List) children.get(object);
                        if (CollectionUtils.isEmpty(list)) {
                            list = new ArrayList();
                        }
                        list.add(obj);
                        children.set(object, list);
                    }
                }
            }
            return t_list;
        }
        return null;
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/11/29 17:24
     * 描述： 数据打包成树形结构
     * 将JSONArray数组转为树状结构
     *
     * @param arr   需要转化的数据
     * @param id    数据唯一的标识键值
     * @param pid   父id唯一标识键值
     * @param child 子节点键值
     **/
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        com.alibaba.fastjson.JSONArray r = new com.alibaba.fastjson.JSONArray();
        com.alibaba.fastjson.JSONObject hash = new com.alibaba.fastjson.JSONObject(); //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            com.alibaba.fastjson.JSONObject json = (com.alibaba.fastjson.JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        } //遍历结果集
        for (int j = 0; j < arr.size(); j++) { //单条记录
            com.alibaba.fastjson.JSONObject aVal = (com.alibaba.fastjson.JSONObject) arr.get(j); //在hash中取出key为单条记录中pid的值
            com.alibaba.fastjson.JSONObject hashVP = (com.alibaba.fastjson.JSONObject) hash.get(aVal.get(pid)); //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) { //检查是否有child属性
                if (hashVP.get(child) != null) {
                    com.alibaba.fastjson.JSONArray ch = (com.alibaba.fastjson.JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    com.alibaba.fastjson.JSONArray ch = new com.alibaba.fastjson.JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }

    /**
     * 作者： liwei
     * 时间：2018年4月11日
     * 描述：生成token
     *
     * @param appkey
     * @return
     */
    public synchronized static final String getToken(String appkey) {
        return AESUtils.encrypt(appkey + numberOnlyRoomId()
                + randomNumber(9) + UUID.randomUUID().toString()).replace("_", "");
    }

    /**
     * 作	者：Li.Wei
     * 时间：2018年4月18日
     * 描	述：获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (NullPointerException e) {
            System.out.println("request未获取到");
        }
        return request;
    }

    /**
     * 作	者：Li.Wei
     * 时间：2018年4月18日
     * 描	述：获取response
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;
        try {
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        } catch (Exception e) {
            System.out.println("response未获取到");
        }
        return response;
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/11/7 16:50
     * 描述： MD5加密
     **/
    public static String MD5(String s) {
        if (s == null) {
            return "";
        }
        try {
            // 利用MD5加密算法对传过来的用户密码进行加密
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bys = digest.digest(s.getBytes());
            // 利用base64算法对密码进行二次加密，使密码成为定长的字符串存储到数据库中
            BASE64Encoder encode = new BASE64Encoder();
            return encode.encode(bys);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return s;
        }
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/11/23 9:44
     * 描述： 请求地址组装
     **/
    public static final String requestUrlAssemble(String path) {
        path = path.replaceAll("//", "/");
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/12/13 15:50
     * 描述： Redis获取SessionBean
     **/
    public static SessionBean getSessionBean(String key) {
        if (Common.isNull(key))  throw new BusinessException("common.tokenInvalid");
        Object data = RedisUtil.get(key);
        if (Common.isNull(data))  throw new BusinessException("common.tokenInvalid");
        return JSON.parseObject(data.toString(), SessionBean.class);
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/12/13 15:50
     * 描述： Request获取SessionBean
     **/
    public static SessionBean getSessionBean(HttpServletRequest request) {
        String key = request.getHeader("FilterToken_MT");
        if (Common.isNull(key))  throw new BusinessException("common.tokenInvalid");
        Object data = RedisUtil.get(key);
        if (Common.isNull(data))  throw new BusinessException("common.tokenInvalid");
        return JSON.parseObject(data.toString(), SessionBean.class);
    }

    /**
     * 作者： Li.Wei
     * 时间： 2018/12/13 15:50
     * 描述： Request获取SessionBean（系统用户）
     **/
    public static SessionBean getSessionBeanToSystem(HttpServletRequest request) {
        String key = request.getHeader("FilterToken_MT");
        if (Common.isNull(key))  throw new BusinessException("common.tokenInvalid");
        Object data = RedisUtil.get(key + PropertiesUtil.get("_PCSYSTEM"));
        if (Common.isNull(data))  throw new BusinessException("common.tokenInvalid");
        return JSON.parseObject(data.toString(), SessionBean.class);
    }
}
