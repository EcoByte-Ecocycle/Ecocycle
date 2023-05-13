package com.ecobyte.ecocycle.domain.report;

import com.ecobyte.ecocycle.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime reportDate;

    public Report(final String productName, final String imageUrl, final User loginUser,
                  final LocalDateTime reportDate) {
        this(null, productName, imageUrl, loginUser, reportDate);
    }

    public Report(final Long id, final String productName, final String imageUrl, final User user,
                  final LocalDateTime reportDate) {
        this.id = id;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.user = user;
        this.reportDate = reportDate;
    }
}
