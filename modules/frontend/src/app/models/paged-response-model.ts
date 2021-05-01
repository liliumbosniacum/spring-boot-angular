export class PagedResponse<DTO> {
    content: DTO[] | undefined;
    count: number | undefined;
    totalCount: number | undefined;
}