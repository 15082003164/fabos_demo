package com.cxxy.demo.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class JSONData {
    private static final String ERROR_MSG = "msg";
    private static final String ERROR_STACK = "errorStack";

    // action执行结果（true|flase）
    private boolean success = true;

    // action执行结果（json插件返回客户端）
    private Map<Object, Object> data = null;

    public Map<Object, Object> getData() {
        return data;
    }

    public void setData(Map<Object, Object> data) {
        this.data = data;
    }

    // action执行结果原因（json插件返回客户端）
    private Map<Object, Object> err = new HashMap<Object, Object>();

    /**
     * @Title: setResult
     * @Description: 设置输出接果
     * @param key
     * @param value
     * @return void
     */
    public void setResult(Object key, Object value) {
        if (data == null) {
            data = new HashMap<Object, Object>();
        }
        data.put(key, value);
    }

    public Map<Object, Object> getErr() {
        return err;
    }

    public void setErr(Map<Object, Object> err) {
        this.err = err;
    }

    /**
     * @Title: setErrorReason
     * @Description: 设置失败原因
     * @param errorMsg
     * @return void
     */
    public void setErrorReason(String errorMsg) {
        if (err == null) {
            err = new HashMap<Object, Object>();
        }

        setSuccess(false);
        this.err.put(ERROR_MSG, errorMsg);
        this.err.put(ERROR_STACK, "");
    }

    public void setErrorReason(String errorMsg, Exception e) {
        if (err == null) {
            err = new HashMap<Object, Object>();
        }

        setSuccess(false);
        this.err.put(ERROR_MSG, errorMsg);
        this.err.put(ERROR_STACK, generateStackTrace(e));
    }

    /**
     * @Title: generateStackTrace
     * @Description: 生成异常堆栈字符串
     * @param e
     * @return
     * @return String
     */
    public String generateStackTrace(Exception e) {
        if (e == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(byteArrayOutputStream));
            stringBuffer.append(byteArrayOutputStream.toString());
        } catch (Exception ex) {
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException ex2) {
                }
            }
        }
        return stringBuffer.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
