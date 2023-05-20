package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.report.Report;
import com.ecobyte.ecocycle.domain.user.User;
import lombok.Getter;

@Getter
public class ReportResponse {

    private final Long id;
    private final String productName;
    private final String imageUrl;
    private final Long userId;
    private final String userNickname;

    public ReportResponse(final Long id, final String productName, final String imageUrl, final Long userId,
                          final String userNickname) {
        this.id = id;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public static ReportResponse from(final Report report) {
        final User user = report.getUser();
        return new ReportResponse(report.getId(), report.getProductName(), report.getImageUrl(), user.getId(),
                user.getNickname());
    }
}
