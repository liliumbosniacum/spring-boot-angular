package com.lilium.springangular.dto.search;

import java.util.List;

public class PagedResponse<DTO> {
    private List<DTO> content;
    private long count;
    private long totalCount;

    public PagedResponse(final List<DTO> content, final long count, final long totalCount) {
        this.content = content;
        this.count = count;
        this.totalCount = totalCount;
    }

    public List<DTO> getContent() {
        return content;
    }

    public void setContent(List<DTO> content) {
        this.content = content;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
