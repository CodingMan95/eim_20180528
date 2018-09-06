package net.eimarketing.eim_20180528.util;

public abstract class Result {
	
	protected boolean status;
	protected String message;
	protected Object data;

	public Result() {
		this(null, null);
	}

	public Result(String message) {
		this(false, message, null);
	}

	public Result(String message, Object data) {
		this(false, message, data);
	}

	public Result(boolean status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public final boolean isStatus() {
		return this.status;
	}

	public final void setStatus(boolean status) {
		this.status = status;
	}

	public final String getMessage() {
		return this.message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}

	public final Object getData() {
		return this.data;
	}

	public final void setData(Object data) {
		this.data = data;
	}
}
