package com.example.demo.vo;

import lombok.Getter;

//@Data
public class ResultData<DT> {
	@Getter
	private String ResultCode;
	@Getter
	private String msg;
	@Getter
	private DT data1;

	public static <DT> ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null);

	}

	// from 이라는 메소드가 실행됐을 때, 어떻게 성공(실패)를 판별하는지
	public static <DT> ResultData<DT> from(String ResultCode, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data1 = data1;

		return rd;
	}

	// 보이는 이유? getter를 만들 때 is+대문자가 규칙이기 때문에 자동으로 필드에 있는 변수가 됨
	public boolean isSuccess() {
		return ResultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public static <DT> ResultData<DT> newData(ResultData rd, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), newData);
	}
}
