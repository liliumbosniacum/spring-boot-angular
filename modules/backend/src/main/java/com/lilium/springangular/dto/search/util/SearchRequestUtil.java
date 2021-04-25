package com.lilium.springangular.dto.search.util;

import com.lilium.springangular.dto.search.PageRequest;
import com.lilium.springangular.dto.search.SearchRequest;

public final class SearchRequestUtil {
    private static final int DEFAULT_PAGE_SIZE = 100;
    private SearchRequestUtil() {}

    public static PageRequest toPageRequest(final SearchRequest request) {
        if (request == null) {
            return new PageRequest(0, DEFAULT_PAGE_SIZE);
        }

        final int requestedSize = request.getSize();
        return new PageRequest(request.getPage(), requestedSize == 0 ? DEFAULT_PAGE_SIZE : requestedSize);
    }
}
