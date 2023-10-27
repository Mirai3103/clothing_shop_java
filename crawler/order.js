import { PrismaClient } from "@prisma/client";
import { faker } from "@faker-js/faker";
import fs from "fs";
import mysql from "mysql2/promise";
import { config } from "dotenv";
import { randomUUID } from "crypto";
config();
const prisma = new PrismaClient();

// model payment {
//   payment_id         String    @id @db.VarChar(36)
//   created_by         String?   @db.VarChar(255)
//   created_date       DateTime? @db.DateTime(6)
//   last_modified_by   String?   @db.VarChar(255)
//   last_modified_date DateTime? @db.DateTime(6)
//   amount             Float     @db.Float
//   payment_details    String    @db.VarChar(255)
//   payment_response   String?   @db.VarChar(255)
//   status             Int       @db.TinyInt
//   order_order_id     String?   @db.VarChar(36)
//   order              order?    @relation(fields: [order_order_id], references: [order_id], onDelete: Restrict, onUpdate: Restrict, map: "FKhobhqk8v7ohgfr56r725x80y5")

//   @@index([order_order_id], map: "FKhobhqk8v7ohgfr56r725x80y5")
// }

// model order_item {
//   order_id          String         @db.VarChar(255)
//   product_option_id Int
//   price             Float
//   quantity          Int
//   order             order          @relation(fields: [order_id], references: [order_id], onUpdate: Restrict, map: "FKryn1sdluxcjfeu891k75myheu")
//   product_option    product_option @relation(fields: [product_option_id], references: [product_option_id], onUpdate: Restrict, map: "FKsoimewh387var4rtx7v1wa2wv")

//   @@id([order_id, product_option_id])
//   @@index([product_option_id], map: "FKsoimewh387var4rtx7v1wa2wv")
// }

// model order {
//   order_id               String       @id @db.VarChar(36)
//   created_by             String?      @db.VarChar(255)
//   created_date           DateTime?    @db.DateTime(6)
//   last_modified_by       String?      @db.VarChar(255)
//   last_modified_date     DateTime?    @db.DateTime(6)
//   address                String?      @db.VarChar(500)
//   cancel_reason          String?      @db.VarChar(500)
//   customer_name          String?      @db.VarChar(100)
//   delivery_fee           Float?
//   note                   String?      @db.VarChar(500)
//   payment_method         Int          @db.TinyInt
//   phone_number           String?      @db.VarChar(20)
//   status                 Int?         @db.TinyInt
//   total_amount           Int?
//   promotion_promotion_id Int?
//   user_user_id           String?      @db.VarChar(255)
//   email                  String?      @db.VarChar(100)
//   promotion              promotion?   @relation(fields: [promotion_promotion_id], references: [promotion_id], onDelete: Restrict, onUpdate: Restrict, map: "FKkgpbw4fb1s5r7v0tlathrxp7s")
//   user                   user?        @relation(fields: [user_user_id], references: [user_id], onDelete: Restrict, onUpdate: Restrict, map: "FKktqkl7kou3pykq8fnm4rr9jmy")
//   order_item             order_item[]
//   payment                payment[]

//   @@index([promotion_promotion_id], map: "FKkgpbw4fb1s5r7v0tlathrxp7s")
//   @@index([user_user_id], map: "FKktqkl7kou3pykq8fnm4rr9jmy")
//   @@map("_order")
// }
(async () => {
  const listUser = await prisma.user.findMany({
    where: {
      is_customer: true,
    },
  });
  const listProductOption = await prisma.product_option.findMany({});
  const listOrders = [];
  const listOrderItems = [];
  for (const user of listUser) {
    if (faker.number.int({ min: 0, max: 15 }) == 0) continue;
    const numOfOrder = faker.number.int({ min: 0, max: 10 });
    for (let i = 0; i < numOfOrder; i++) {
      const numOfOrderItem = faker.number.int({ min: 1, max: 5 });
      const orderItems = [];
      let totalAmount = 0;
      const id = randomUUID();

      for (let j = 0; j < numOfOrderItem; j++) {
        const productOption = faker.helpers.arrayElement(listProductOption);
        let max = 2;
        if (faker.number.int({ min: 0, max: 15 }) == 0) {
          max = 5;
        }
        const quantity = faker.number.int({ min: 1, max });
        const product = await findProductById(productOption.product_product_id);
        const rawPrice = product.price;
        const price = Math.round(rawPrice * (1 - product.discount / 100));
        console.log(price);
        orderItems.push({
          order_id: id,
          product_option_id: productOption.product_option_id,
          price,
          quantity,
        });
        totalAmount += price * quantity;
      }
      const paymentMethod = faker.helpers.arrayElement([0, 1, 2]);
      const status = faker.helpers.arrayElement([0, 1, 2, 3, 4, 5]);
      const order = {
        order_id: id,
        address: user.address,
        customer_name: user.first_name + " " + user.last_name,
        delivery_fee: randomVietNameseMoney({ min: 10000, max: 40000 }),
        note: faker.lorem.sentences({ max: 50 }),
        payment_method: paymentMethod,
        phone_number: user.phone_number,
        status: status,
        total_amount: totalAmount,
        user_user_id: user.user_id,
        email: user.email,
        created_date: faker.date.past({
          refDate: new Date(),
          years: 2,
        }),
        created_by: user.user_id,
      };
      listOrders.push(order);
      listOrderItems.push(...orderItems);
    }
  }

  await prisma.order.createMany({
    data: listOrders,
  });
  await prisma.order_item.createMany({
    data: listOrderItems,
  });
  console.log("done");
})();

async function findProductById(id) {
  const product = await prisma.product.findUnique({
    where: {
      product_id: id,
    },
  });
  return product;
}

function randomVietNameseMoney({ min = 0, max = 100000000 }) {
  const amount = faker.number.int({ min, max });
  // remove last 3 digits
  const amountString = amount.toString();
  const length = amountString.length;
  const newAmountString = amountString.slice(0, length - 3);
  const newAmount = Number(newAmountString + "000");
  return newAmount;
}

// public enum PaymentStatus {
//     PENDING, => 0
//     PAID, => 1
//     CANCELLED, => 2
//     REFUNDED, => 3
//     FAILED, => 4
// }

// model payment {
//   payment_id         String    @id @db.VarChar(36)
//   created_by         String?   @db.VarChar(255)
//   created_date       DateTime? @db.DateTime(6)
//   last_modified_by   String?   @db.VarChar(255)
//   last_modified_date DateTime? @db.DateTime(6)
//   amount             Float     @db.Float
//   payment_details    String    @db.VarChar(255)
//   payment_response   String?   @db.VarChar(255)
//   status             Int       @db.TinyInt
//   order_order_id     String?   @db.VarChar(36)
//   order              order?    @relation(fields: [order_order_id], references: [order_id], onDelete: Restrict, onUpdate: Restrict, map: "FKhobhqk8v7ohgfr56r725x80y5")

//   @@index([order_order_id], map: "FKhobhqk8v7ohgfr56r725x80y5")
// }

async function createFakePayment() {
  const listOrders = await prisma.order.findMany({});
  const listPayment = [];
  for (const order of listOrders) {
    const isFailed = faker.number.int({ min: 0, max: 6 }) == 0;
    const refDate = faker.date.soon({
      refDate: order.created_date,
      days: 2,
    });
    if (isFailed) {
      const payment = {
        payment_id: randomUUID(),
        amount: order.total_amount,
        payment_details: "Thanh toán cho đơn hàng " + order.order_id + ".",
        status: 4,
        order_order_id: order.order_id,
        created_by: order.user_user_id,
        created_date: refDate,
      };
      listPayment.push(payment);
    }
    const payment = {
      payment_id: randomUUID(),
      amount: order.total_amount,
      payment_details: "Thanh toán cho đơn hàng " + order.order_id + ".",
      status: 1,
      order_order_id: order.order_id,
      created_by: order.user_user_id,
      created_date: faker.date.soon({
        refDate: refDate,
        days: 2,
      }),
    };
    listPayment.push(payment);
  }
  await prisma.payment.createMany({
    data: listPayment,
  });
}

// createFakePayment();
