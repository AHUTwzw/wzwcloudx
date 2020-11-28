package com.wzw.springcloud.exception;

import com.wzw.springcloud.constant.SeverityConstant;

/**
 * @author wuzhiwei
 * @create 2020-11-29 2:53
 */
public class CloudRuntimeException extends RuntimeException {
    /** serialVersionUID */
    private static final long serialVersionUID = 0L;

    /** 严重级别 */
    protected int severity = SeverityConstant.NORMAL;

    /**
     * 空构造器。
     */
    public CloudRuntimeException() {
        super();
    }

    /**
     * 构造器。
     *
     * @param severity 严重级别
     */
    public CloudRuntimeException(int severity) {
        super();

        this.severity = severity;
    }

    /**
     * 构造器。
     *
     * @param message 消息
     */
    public CloudRuntimeException(String message) {
        super(message);
    }

    /**
     * 构造器。
     *
     * @param message 消息
     * @param severity 严重级别
     */
    public CloudRuntimeException(String message, int severity) {
        super(message);

        this.severity = severity;
    }

    /**
     * 构造器。
     *
     * @param cause 原因
     */
    public CloudRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器。
     *
     * @param cause 原因
     * @param severity 严重级别
     */
    public CloudRuntimeException(Throwable cause, int severity) {
        super(cause);

        this.severity = severity;
    }

    /**
     * 构造器。
     *
     * @param message 消息
     * @param cause 原因
     */
    public CloudRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造器。
     *
     * @param message 消息
     * @param cause 原因
     * @param severity 严重级别
     */
    public CloudRuntimeException(String message, Throwable cause, int severity) {
        super(message, cause);

        this.severity = severity;
    }

    /**
     * @return Returns the severity.
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString()).append(" - severity: ");

        switch (severity) {
            case SeverityConstant.MINOR:
                buffer.append("MINOR");
                break;

            case SeverityConstant.NORMAL:
                buffer.append("NORMAL");
                break;

            case SeverityConstant.MAJOR:
                buffer.append("MAJOR");
                break;

            case SeverityConstant.CRITICAL:
                buffer.append("CRITICAL");
                break;

            default:
                buffer.append("UNKNOWN");
        }

        buffer.append("(").append(severity).append(")");

        return buffer.toString();
    }
}
