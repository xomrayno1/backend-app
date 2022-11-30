package com.app.model.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApprovedStep {
	WATTING(1),
	;

	private final int value;
}
