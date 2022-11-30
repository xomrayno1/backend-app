package com.app.model.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
	ACTIVE(1),
	IN_ACTIVE(0)
	;

	private final int value;
}
