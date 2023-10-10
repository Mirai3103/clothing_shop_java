import type { AxiosInstance, AxiosResponse, CancelToken } from 'axios';
export declare class Client {
    private instance;
    private baseUrl;
    protected jsonParseReviver: ((key: string, value: any) => any) | undefined;
    constructor(baseUrl?: string, instance?: AxiosInstance);
    /**
     * @return OK
     */
    createCategory(body: UpdateCategoryCommand, cancelToken?: CancelToken | undefined): Promise<boolean>;
    protected processCreateCategory(response: AxiosResponse): Promise<boolean>;
    /**
     * @return OK
     */
    updateCartItemQuantity(body: UpdateCartItemQuantityCommand, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processUpdateCartItemQuantity(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    ipn(param: MomoCallbackParam, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processIpn(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    getMyProfile(cancelToken?: CancelToken | undefined): Promise<UserDto>;
    protected processGetMyProfile(response: AxiosResponse): Promise<UserDto>;
    /**
     * @return OK
     */
    updateMyProfile(body: UpdateProfileCommand, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processUpdateMyProfile(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    createProduct(body: CreateProductCommand, cancelToken?: CancelToken | undefined): Promise<number>;
    protected processCreateProduct(response: AxiosResponse): Promise<number>;
    /**
     * @return OK
     */
    createPayment(body: CreatePaymentCommand, cancelToken?: CancelToken | undefined): Promise<CreatePaymentResponse>;
    protected processCreatePayment(response: AxiosResponse): Promise<CreatePaymentResponse>;
    /**
     * @return OK
     */
    createOrder(body: CreateOrderCommand, cancelToken?: CancelToken | undefined): Promise<string>;
    protected processCreateOrder(response: AxiosResponse): Promise<string>;
    /**
     * @param body (optional)
     * @return OK
     */
    uploadFile(body: Body | undefined, cancelToken?: CancelToken | undefined): Promise<string>;
    protected processUploadFile(response: AxiosResponse): Promise<string>;
    /**
     * @return OK
     */
    createColor(body: CreateColorCommand, cancelToken?: CancelToken | undefined): Promise<number>;
    protected processCreateColor(response: AxiosResponse): Promise<number>;
    /**
     * @return OK
     */
    addToCart(body: AddToCartCommand, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processAddToCart(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    callback(param: MomoCallbackParam, cancelToken?: CancelToken | undefined): Promise<string>;
    protected processCallback(response: AxiosResponse): Promise<string>;
    /**
     * @return OK
     */
    getProductById(productId: number, cancelToken?: CancelToken | undefined): Promise<ProductDetailDto>;
    protected processGetProductById(response: AxiosResponse): Promise<ProductDetailDto>;
    /**
     * @param categoryId (optional)
     * @param forGender (optional)
     * @param minPrice (optional)
     * @param maxPrice (optional)
     * @param page (optional)
     * @param size (optional)
     * @param sortField (optional)
     * @param sortDir (optional)
     * @param keyword (optional)
     * @return OK
     */
    getProducts(categoryId: number | undefined, forGender: ForGender | undefined, minPrice: number | undefined, maxPrice: number | undefined, page: number | undefined, size: number | undefined, sortField: string | undefined, sortDir: string | undefined, keyword: string | undefined, cancelToken?: CancelToken | undefined): Promise<PaginatedProductBriefDto>;
    protected processGetProducts(response: AxiosResponse): Promise<PaginatedProductBriefDto>;
    /**
     * @param totalPrice (optional)
     * @return OK
     */
    getDeliveryFee(toProvince: string, toDistrict: string, toWard: string, totalPrice: number | undefined, cancelToken?: CancelToken | undefined): Promise<number>;
    protected processGetDeliveryFee(response: AxiosResponse): Promise<number>;
    /**
     * @param eyword (optional)
     * @param page (optional)
     * @param size (optional)
     * @param sortField (optional)
     * @param sortDir (optional)
     * @param keyword (optional)
     * @return OK
     */
    getCategories(eyword: string | undefined, page: number | undefined, size: number | undefined, sortField: string | undefined, sortDir: string | undefined, keyword: string | undefined, cancelToken?: CancelToken | undefined): Promise<PaginatedCategoryBriefDto>;
    protected processGetCategories(response: AxiosResponse): Promise<PaginatedCategoryBriefDto>;
    /**
     * @return OK
     */
    getMyCart(cancelToken?: CancelToken | undefined): Promise<CartItemDto[]>;
    protected processGetMyCart(response: AxiosResponse): Promise<CartItemDto[]>;
    /**
     * @return OK
     */
    deleteCategory(id: string, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processDeleteCategory(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    clearCart(productOptionId: number, cancelToken?: CancelToken | undefined): Promise<void>;
    protected processClearCart(response: AxiosResponse): Promise<void>;
}
export declare class CreateProductClient {
    private instance;
    private baseUrl;
    protected jsonParseReviver: ((key: string, value: any) => any) | undefined;
    constructor(baseUrl?: string, instance?: AxiosInstance);
    /**
     * @return OK
     */
    1(body: CreateProductOptionCommand, cancelToken?: CancelToken | undefined): Promise<void>;
    protected process1(response: AxiosResponse): Promise<void>;
    /**
     * @return OK
     */
    2(body: CreateProductImageCommand, cancelToken?: CancelToken | undefined): Promise<void>;
    protected process2(response: AxiosResponse): Promise<void>;
}
export declare class CreateCategoryClient {
    private instance;
    private baseUrl;
    protected jsonParseReviver: ((key: string, value: any) => any) | undefined;
    constructor(baseUrl?: string, instance?: AxiosInstance);
    /**
     * @return OK
     */
    1(body: CreateCategoryCommand, cancelToken?: CancelToken | undefined): Promise<number>;
    protected process1(response: AxiosResponse): Promise<number>;
}
export declare class ClearCartClient {
    private instance;
    private baseUrl;
    protected jsonParseReviver: ((key: string, value: any) => any) | undefined;
    constructor(baseUrl?: string, instance?: AxiosInstance);
    /**
     * @return OK
     */
    1(cancelToken?: CancelToken | undefined): Promise<void>;
    protected process1(response: AxiosResponse): Promise<void>;
}
export declare class UpdateCategoryCommand implements IUpdateCategoryCommand {
    id: number;
    name: string;
    slug: string;
    parentId?: number;
    [key: string]: any;
    constructor(data?: IUpdateCategoryCommand);
    init(_data?: any): void;
    static fromJS(data: any): UpdateCategoryCommand;
    toJSON(data?: any): any;
}
export interface IUpdateCategoryCommand {
    id: number;
    name: string;
    slug: string;
    parentId?: number;
    [key: string]: any;
}
export declare class UpdateCartItemQuantityCommand implements IUpdateCartItemQuantityCommand {
    productOptionId?: number;
    newQuantity?: number;
    [key: string]: any;
    constructor(data?: IUpdateCartItemQuantityCommand);
    init(_data?: any): void;
    static fromJS(data: any): UpdateCartItemQuantityCommand;
    toJSON(data?: any): any;
}
export interface IUpdateCartItemQuantityCommand {
    productOptionId?: number;
    newQuantity?: number;
    [key: string]: any;
}
export declare class MomoCallbackParam implements IMomoCallbackParam {
    partnerCode?: string;
    orderId?: string;
    requestId?: string;
    amount?: number;
    orderInfo?: string;
    orderType?: string;
    transId?: string;
    resultCode?: number;
    message?: string;
    payType?: string;
    responseTime?: number;
    extraData?: string;
    signature?: string;
    [key: string]: any;
    constructor(data?: IMomoCallbackParam);
    init(_data?: any): void;
    static fromJS(data: any): MomoCallbackParam;
    toJSON(data?: any): any;
}
export interface IMomoCallbackParam {
    partnerCode?: string;
    orderId?: string;
    requestId?: string;
    amount?: number;
    orderInfo?: string;
    orderType?: string;
    transId?: string;
    resultCode?: number;
    message?: string;
    payType?: string;
    responseTime?: number;
    extraData?: string;
    signature?: string;
    [key: string]: any;
}
export declare class UpdateProfileCommand implements IUpdateProfileCommand {
    firstName: string;
    lastName: string;
    email?: string;
    address?: string;
    phoneNumber?: string;
    [key: string]: any;
    constructor(data?: IUpdateProfileCommand);
    init(_data?: any): void;
    static fromJS(data: any): UpdateProfileCommand;
    toJSON(data?: any): any;
}
export interface IUpdateProfileCommand {
    firstName: string;
    lastName: string;
    email?: string;
    address?: string;
    phoneNumber?: string;
    [key: string]: any;
}
export declare class CreateProductCommand implements ICreateProductCommand {
    name: string;
    forGender?: CreateProductCommandForGender;
    description: string;
    price: number;
    discount?: number;
    displayImage: string;
    brandId?: number;
    categoryId: number;
    [key: string]: any;
    constructor(data?: ICreateProductCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateProductCommand;
    toJSON(data?: any): any;
}
export interface ICreateProductCommand {
    name: string;
    forGender?: CreateProductCommandForGender;
    description: string;
    price: number;
    discount?: number;
    displayImage: string;
    brandId?: number;
    categoryId: number;
    [key: string]: any;
}
export declare class CreateProductOptionCommand implements ICreateProductOptionCommand {
    colorId: number;
    size: string;
    stock?: number;
    productId: number;
    [key: string]: any;
    constructor(data?: ICreateProductOptionCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateProductOptionCommand;
    toJSON(data?: any): any;
}
export interface ICreateProductOptionCommand {
    colorId: number;
    size: string;
    stock?: number;
    productId: number;
    [key: string]: any;
}
export declare class CreateProductImageCommand implements ICreateProductImageCommand {
    productId?: number;
    colorId?: number;
    url?: string;
    [key: string]: any;
    constructor(data?: ICreateProductImageCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateProductImageCommand;
    toJSON(data?: any): any;
}
export interface ICreateProductImageCommand {
    productId?: number;
    colorId?: number;
    url?: string;
    [key: string]: any;
}
export declare class CreatePaymentCommand implements ICreatePaymentCommand {
    orderId?: string;
    [key: string]: any;
    constructor(data?: ICreatePaymentCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreatePaymentCommand;
    toJSON(data?: any): any;
}
export interface ICreatePaymentCommand {
    orderId?: string;
    [key: string]: any;
}
export declare class CreatePaymentResponse implements ICreatePaymentResponse {
    paymentId?: string;
    paymentMethod?: CreatePaymentResponsePaymentMethod;
    orderId?: string;
    redirectUrl?: string;
    mobileUrl?: string;
    redirect?: boolean;
    [key: string]: any;
    constructor(data?: ICreatePaymentResponse);
    init(_data?: any): void;
    static fromJS(data: any): CreatePaymentResponse;
    toJSON(data?: any): any;
}
export interface ICreatePaymentResponse {
    paymentId?: string;
    paymentMethod?: CreatePaymentResponsePaymentMethod;
    orderId?: string;
    redirectUrl?: string;
    mobileUrl?: string;
    redirect?: boolean;
    [key: string]: any;
}
export declare class CreateOrderCommand implements ICreateOrderCommand {
    productOptionIds: number[];
    customerName: string;
    address: string;
    phoneNumber: string;
    note: string;
    promotionCode?: string;
    paymentMethod: CreateOrderCommandPaymentMethod;
    [key: string]: any;
    constructor(data?: ICreateOrderCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateOrderCommand;
    toJSON(data?: any): any;
}
export interface ICreateOrderCommand {
    productOptionIds: number[];
    customerName: string;
    address: string;
    phoneNumber: string;
    note: string;
    promotionCode?: string;
    paymentMethod: CreateOrderCommandPaymentMethod;
    [key: string]: any;
}
export declare class CreateColorCommand implements ICreateColorCommand {
    name: string;
    image?: string;
    [key: string]: any;
    constructor(data?: ICreateColorCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateColorCommand;
    toJSON(data?: any): any;
}
export interface ICreateColorCommand {
    name: string;
    image?: string;
    [key: string]: any;
}
export declare class CreateCategoryCommand implements ICreateCategoryCommand {
    name: string;
    parentId?: number;
    [key: string]: any;
    constructor(data?: ICreateCategoryCommand);
    init(_data?: any): void;
    static fromJS(data: any): CreateCategoryCommand;
    toJSON(data?: any): any;
}
export interface ICreateCategoryCommand {
    name: string;
    parentId?: number;
    [key: string]: any;
}
export declare class AddToCartCommand implements IAddToCartCommand {
    productOptionId?: number;
    quantity?: number;
    [key: string]: any;
    constructor(data?: IAddToCartCommand);
    init(_data?: any): void;
    static fromJS(data: any): AddToCartCommand;
    toJSON(data?: any): any;
}
export interface IAddToCartCommand {
    productOptionId?: number;
    quantity?: number;
    [key: string]: any;
}
export declare class UserDto implements IUserDto {
    userId?: string;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    address?: string;
    avatarUrl?: string;
    createdAt?: Date;
    permissions?: string[];
    emailVerified?: boolean;
    customer?: boolean;
    accountEnabled?: boolean;
    [key: string]: any;
    constructor(data?: IUserDto);
    init(_data?: any): void;
    static fromJS(data: any): UserDto;
    toJSON(data?: any): any;
}
export interface IUserDto {
    userId?: string;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    address?: string;
    avatarUrl?: string;
    createdAt?: Date;
    permissions?: string[];
    emailVerified?: boolean;
    customer?: boolean;
    accountEnabled?: boolean;
    [key: string]: any;
}
export declare class CategoryBriefDto implements ICategoryBriefDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    categoryId?: number;
    name?: string;
    slug?: string;
    parent?: CategoryBriefDto;
    [key: string]: any;
    constructor(data?: ICategoryBriefDto);
    init(_data?: any): void;
    static fromJS(data: any): CategoryBriefDto;
    toJSON(data?: any): any;
}
export interface ICategoryBriefDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    categoryId?: number;
    name?: string;
    slug?: string;
    parent?: CategoryBriefDto;
    [key: string]: any;
}
export declare class ColorDto implements IColorDto {
    colorId?: number;
    name?: string;
    image?: string;
    [key: string]: any;
    constructor(data?: IColorDto);
    init(_data?: any): void;
    static fromJS(data: any): ColorDto;
    toJSON(data?: any): any;
}
export interface IColorDto {
    colorId?: number;
    name?: string;
    image?: string;
    [key: string]: any;
}
export declare class ProductDetailDto implements IProductDetailDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productId?: number;
    name?: string;
    forGender?: ProductDetailDtoForGender;
    slug?: string;
    price?: number;
    discount?: number;
    displayImage?: string;
    category?: CategoryBriefDto;
    productOptions?: ProductOptionDto[];
    images?: ProductImageDto[];
    description?: string;
    [key: string]: any;
    constructor(data?: IProductDetailDto);
    init(_data?: any): void;
    static fromJS(data: any): ProductDetailDto;
    toJSON(data?: any): any;
}
export interface IProductDetailDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productId?: number;
    name?: string;
    forGender?: ProductDetailDtoForGender;
    slug?: string;
    price?: number;
    discount?: number;
    displayImage?: string;
    category?: CategoryBriefDto;
    productOptions?: ProductOptionDto[];
    images?: ProductImageDto[];
    description?: string;
    [key: string]: any;
}
export declare class ProductImageDto implements IProductImageDto {
    url?: string;
    forColor?: ColorDto;
    [key: string]: any;
    constructor(data?: IProductImageDto);
    init(_data?: any): void;
    static fromJS(data: any): ProductImageDto;
    toJSON(data?: any): any;
}
export interface IProductImageDto {
    url?: string;
    forColor?: ColorDto;
    [key: string]: any;
}
export declare class ProductOptionDto implements IProductOptionDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productOptionId?: number;
    size?: string;
    stock?: number;
    deletedDate?: Date;
    color?: ColorDto;
    [key: string]: any;
    constructor(data?: IProductOptionDto);
    init(_data?: any): void;
    static fromJS(data: any): ProductOptionDto;
    toJSON(data?: any): any;
}
export interface IProductOptionDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productOptionId?: number;
    size?: string;
    stock?: number;
    deletedDate?: Date;
    color?: ColorDto;
    [key: string]: any;
}
export declare class PaginatedProductBriefDto implements IPaginatedProductBriefDto {
    data?: ProductBriefDto[];
    page?: number;
    size?: number;
    totalPages?: number;
    totalElements?: number;
    hasNext?: boolean;
    hasPrevious?: boolean;
    [key: string]: any;
    constructor(data?: IPaginatedProductBriefDto);
    init(_data?: any): void;
    static fromJS(data: any): PaginatedProductBriefDto;
    toJSON(data?: any): any;
}
export interface IPaginatedProductBriefDto {
    data?: ProductBriefDto[];
    page?: number;
    size?: number;
    totalPages?: number;
    totalElements?: number;
    hasNext?: boolean;
    hasPrevious?: boolean;
    [key: string]: any;
}
export declare class ProductBriefDto implements IProductBriefDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productId?: number;
    name?: string;
    forGender?: ProductBriefDtoForGender;
    slug?: string;
    price?: number;
    discount?: number;
    displayImage?: string;
    category?: CategoryBriefDto;
    [key: string]: any;
    constructor(data?: IProductBriefDto);
    init(_data?: any): void;
    static fromJS(data: any): ProductBriefDto;
    toJSON(data?: any): any;
}
export interface IProductBriefDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productId?: number;
    name?: string;
    forGender?: ProductBriefDtoForGender;
    slug?: string;
    price?: number;
    discount?: number;
    displayImage?: string;
    category?: CategoryBriefDto;
    [key: string]: any;
}
export declare class PaginatedCategoryBriefDto implements IPaginatedCategoryBriefDto {
    data?: CategoryBriefDto[];
    page?: number;
    size?: number;
    totalPages?: number;
    totalElements?: number;
    hasNext?: boolean;
    hasPrevious?: boolean;
    [key: string]: any;
    constructor(data?: IPaginatedCategoryBriefDto);
    init(_data?: any): void;
    static fromJS(data: any): PaginatedCategoryBriefDto;
    toJSON(data?: any): any;
}
export interface IPaginatedCategoryBriefDto {
    data?: CategoryBriefDto[];
    page?: number;
    size?: number;
    totalPages?: number;
    totalElements?: number;
    hasNext?: boolean;
    hasPrevious?: boolean;
    [key: string]: any;
}
export declare class CartItemDto implements ICartItemDto {
    userId?: string;
    productOptionId?: number;
    quantity?: number;
    productOption?: ProductOptionDetailDto;
    [key: string]: any;
    constructor(data?: ICartItemDto);
    init(_data?: any): void;
    static fromJS(data: any): CartItemDto;
    toJSON(data?: any): any;
}
export interface ICartItemDto {
    userId?: string;
    productOptionId?: number;
    quantity?: number;
    productOption?: ProductOptionDetailDto;
    [key: string]: any;
}
export declare class ProductOptionDetailDto implements IProductOptionDetailDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productOptionId?: number;
    size?: string;
    stock?: number;
    deletedDate?: Date;
    color?: ColorDto;
    product?: ProductBriefDto;
    [key: string]: any;
    constructor(data?: IProductOptionDetailDto);
    init(_data?: any): void;
    static fromJS(data: any): ProductOptionDetailDto;
    toJSON(data?: any): any;
}
export interface IProductOptionDetailDto {
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
    productOptionId?: number;
    size?: string;
    stock?: number;
    deletedDate?: Date;
    color?: ColorDto;
    product?: ProductBriefDto;
    [key: string]: any;
}
export declare class Body implements IBody {
    file?: string;
    [key: string]: any;
    constructor(data?: IBody);
    init(_data?: any): void;
    static fromJS(data: any): Body;
    toJSON(data?: any): any;
}
export interface IBody {
    file?: string;
    [key: string]: any;
}
export declare enum ForGender {
    FOR_MALE = "FOR_MALE",
    FOR_FEMALE = "FOR_FEMALE",
    FOR_BOTH = "FOR_BOTH"
}
export declare enum CreateProductCommandForGender {
    FOR_MALE = "FOR_MALE",
    FOR_FEMALE = "FOR_FEMALE",
    FOR_BOTH = "FOR_BOTH"
}
export declare enum CreatePaymentResponsePaymentMethod {
    COD = "COD",
    MOMO_QR = "MOMO_QR",
    MOMO_ATM = "MOMO_ATM"
}
export declare enum CreateOrderCommandPaymentMethod {
    COD = "COD",
    MOMO_QR = "MOMO_QR",
    MOMO_ATM = "MOMO_ATM"
}
export declare enum ProductDetailDtoForGender {
    FOR_MALE = "FOR_MALE",
    FOR_FEMALE = "FOR_FEMALE",
    FOR_BOTH = "FOR_BOTH"
}
export declare enum ProductBriefDtoForGender {
    FOR_MALE = "FOR_MALE",
    FOR_FEMALE = "FOR_FEMALE",
    FOR_BOTH = "FOR_BOTH"
}
export interface FileParameter {
    data: any;
    fileName: string;
}
export declare class ApiException extends Error {
    message: string;
    status: number;
    response: string;
    headers: {
        [key: string]: any;
    };
    result: any;
    constructor(message: string, status: number, response: string, headers: {
        [key: string]: any;
    }, result: any);
    protected isApiException: boolean;
    static isApiException(obj: any): obj is ApiException;
}
//# sourceMappingURL=client.d.ts.map