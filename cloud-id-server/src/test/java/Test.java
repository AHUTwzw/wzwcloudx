import com.alibaba.fastjson.JSONObject;

/**
 * @author wuzhiwei
 * @create 2020-12-13 19:05
 */
public class Test {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("datacenterId", 100);
        jsonObject.put("workerId", 100);
        System.out.println(jsonObject.toJSONString());
    }
}
