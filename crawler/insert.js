import { PrismaClient } from "@prisma/client";
import fs from "fs";

const prisma = new PrismaClient();

//  {
//         "description": "### **![](https://mcdn.coolmate.me/image/February2023/mceclip0_17.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip1_23.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip2_39.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip3_67.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip4_35.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip5_65.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip6_60.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip7_92.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip8_65.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip9_81.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip10_88.jpg)**\n\n**![](https://mcdn.coolmate.me/image/February2023/mceclip11_43.jpg)**\n\n**![](https://mcdn.coolmate.me/image/January2023/dri-breathe-sat-nach-111_75.jpg)**\n\n### **Cách bảo quản Quần short nam thể thao Recycle 7\" thoáng khí**\n\n* Không nên giặt áo trong nước nóng quá 40 độ, vì nước nóng có thể làm vải giãn ra và gây hư hỏng.\n* Không nên sử dụng các loại xà phòng giặt tẩy mạnh, tuyệt đối không dùng thuốc tẩy, nhất là trên quần màu\n* Nếu được, nên tránh giặt bằng máy và thường xuyên giặt bằng tay\n* Khi phơi, các bạn nên lộn trái quần thun lại và nên phơi ở chổ mát. Ánh mặt trời cũng là một nguyên nhân gây bay màu\n* Để hạn chế sự co giãn của chúng bạn nên phơi ngang trên dây. Nếu phơi bằng móc, bạn có thể khiến quần bị chảy dài gây mất thẩm mỹ.",
//         "discount": "-21%",
//         "rawPrice": "239.000đ",
//         "afterDiscountPrice": "189.000đ",
//         "displayImage": "https://media.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/August2022/DSC01693-copyxanhreu_93.jpg",
//         "title": "Shorts thể thao 7\"",
//         "category": "Quần Short Nam",
//         "productOptions": [
//             {
//                 "color": "Xanh rêu",
//                 "gallery": [
//                     "https://media.coolmate.me/image/August2022/DSC01693-copyxanhreu_93.jpg",
//                     "https://media.coolmate.me/image/August2022/DSC01693-copyreuxanh.jpg",
//                     "https://media.coolmate.me/image/August2022/reu_1111.jpg",
//                     "https://media.coolmate.me/image/August2022/reu_3.jpg",
//                     "https://media.coolmate.me/image/August2022/reu_5.jpg",
//                     "https://media.coolmate.me/image/August2022/reu_6.jpg",
//                     "https://media.coolmate.me/image/August2022/_CMM1056_copy_57.jpg"
//                 ],
//                 "size": ["M", "L", "XL", "2XL", "3XL"]
//             },
//             {
//                 "color": "Xanh ngọc",
//                 "gallery": [
//                     "https://media.coolmate.me/image/February2023/7_recycle_xanhngoc_1.jpg",
//                     "https://media.coolmate.me/image/February2023/7_recycle_xanhngoc2.jpg",
//                     "https://media.coolmate.me/image/August2022/DSC01693-copyxanhngoc_99.jpg",
//                     "https://media.coolmate.me/image/August2022/DSC01693-copyngoccxanh.jpg",
//                     "https://media.coolmate.me/image/August2022/xanh_ngoc_5.jpg",
//                     "https://media.coolmate.me/image/August2022/xanh_ngoc_6.jpg",
//                     "https://media.coolmate.me/image/August2022/_CMM1082_copy_11.jpg"
//                 ],
//                 "size": ["M", "L", "XL", "2XL", "3XL"]
//             },
//             {
//                 "color": "Đen",
//                 "gallery": [
//                     "https://media.coolmate.me/image/August2022/DSC01693-copydenn_64.jpg",
//                     "https://media.coolmate.me/image/May2022/recycle7den.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01581-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01585-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01590-copy.jpg",
//                     "https://media.coolmate.me/uploads/April2022/IMG_3528_16_copy.jpg"
//                 ],
//                 "size": ["M", "L", "XL", "2XL", "3XL"]
//             },
//             {
//                 "color": "Xám chì",
//                 "gallery": [
//                     "https://media.coolmate.me/image/May2022/DSC01693-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/recycle7chii.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01686-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01699-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01705-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01701-copy.jpg",
//                     "https://media.coolmate.me/uploads/March2022/IMG_3528_16.jpg"
//                 ],
//                 "size": ["M", "L", "XL", "2XL", "3XL"]
//             },
//             {
//                 "color": "Xám xanh",
//                 "gallery": [
//                     "https://media.coolmate.me/image/August2022/DSC01693-copyxammxanh_5.jpg",
//                     "https://media.coolmate.me/image/May2022/recycle7xam.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01663-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01669-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01675-copy.jpg",
//                     "https://media.coolmate.me/image/May2022/DSC01679-copy.jpg",
//                     "https://media.coolmate.me/uploads/March2022/IMG_3493-2_86.jpg"
//                 ],
//                 "size": ["M", "L", "XL", "2XL", "3XL"]
//             }
//         ],
//         "features": "## Tính năng \n\n-Chất liệu: 100% sợi Recycled Polyester\n\n- Xử lý hoàn thiện vải: Quick-Dry + Wicking + Stretch\n\n- Vải Recycle dệt kiểu Double Weaving mang lại cảm giác Cooling khi mặc\n\n- Phù hợp với: vận động thể thao\n\n- Độ dài quần: 7 inch\n\n-  Tự hào sản xuất tại Việt Nam\\* [Xem nhà máy >](https://s.coolmate.me/fpxm8) \n\n- Người mẫu: 186 cm - 72 kg, mặc quần size XL"
//     },

async function insert(item) {
    let existCategory = await prisma.category.findFirst({
        where: {
            name: item.category.trim(),
        },
    });
    if (!existCategory) {
        existCategory = await prisma.category.create({
            data: {
                name: item.category.trim(),
                slug: toSlug(item.category.trim()),
                created_by: "system",
                created_date: new Date(),
            },
        });
        console.log("inserted category: ", existCategory.category_id);
    }

    const product = await prisma.product.create({
        data: {
            description: item.features + "\n\n" + item.description,
            discount: Number(item.discount.replace(/[^0-9]/g, "")) || 0,
            price: Number(item.rawPrice.replace(/[^0-9]/g, "")) || (Math.floor(Math.random() * 500) + 50) * 1000,
            display_image: item.displayImage,
            for_gender: 0,
            name: item.title,
            slug: toSlug(item.title.trim()),
            category_category_id: existCategory.category_id,
            created_by: "system",
            created_date: new Date(),
        },
    });
    console.log("inserted product: ", product.product_id);
    const productOptions = item.productOptions;
    const idColorMap = {};
    for await (const productOption of productOptions) {
        let existColor = await prisma.color.findFirst({
            where: {
                name: productOption.color.trim(),
            },
        });
        if (!existColor) {
            existColor = await prisma.color.create({
                data: {
                    name: productOption.color.trim(),
                    created_by: "system",
                    created_date: new Date(),
                },
            });
            console.log("inserted color: ", existColor.color_id);
        }
        idColorMap[productOption.color.trim()] = existColor.color_id;

        for await (const size of productOption.size) {
            const newProductOption = await prisma.product_option.create({
                data: {
                    color_color_id: existColor.color_id,
                    size: size.trim(),
                    stock: Math.floor(Math.random() * 1000) + 50,
                    product_product_id: product.product_id,
                    created_by: "system",
                    created_date: new Date(),
                },
            });
        }
    }
    for await (const productOption of productOptions) {
        const id = idColorMap[productOption.color.trim()];
        for await (const image of productOption.gallery) {
            try {
                await prisma.product_image.create({
                    data: {
                        url: image,
                        for_color_color_id: id,
                        created_by: "system",
                        created_date: new Date(),
                        product_product_id: product.id,
                    },
                });
            } catch (e) {}
        }
    }
}

async function main() {
    const dataSTR = fs.readFileSync("detail.json");
    const data = JSON.parse(dataSTR);
    for await (const item of data) {
        await insert(item);
    }
}

main().then(() => {
    console.log("done");
});

function toSlug(str) {
    // Chuyển hết sang chữ thường
    str = str.toLowerCase();

    // xóa dấu
    str = str.replace(/(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)/g, "a");
    str = str.replace(/(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)/g, "e");
    str = str.replace(/(ì|í|ị|ỉ|ĩ)/g, "i");
    str = str.replace(/(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)/g, "o");
    str = str.replace(/(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)/g, "u");
    str = str.replace(/(ỳ|ý|ỵ|ỷ|ỹ)/g, "y");
    str = str.replace(/(đ)/g, "d");

    // Xóa ký tự đặc biệt
    str = str.replace(/([^0-9a-z-\s])/g, "");

    // Xóa khoảng trắng thay bằng ký tự -
    str = str.replace(/(\s+)/g, "-");

    // Xóa ký tự - liên tiếp
    str = str.replace(/-+/g, "-");

    // xóa phần dự - ở đầu
    str = str.replace(/^-+/g, "");

    // xóa phần dư - ở cuối
    str = str.replace(/-+$/g, "");

    // return
    return str;
}
