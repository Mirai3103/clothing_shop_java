package com.shop.clothing.category.commands.deleteCategory;

import com.shop.clothing.common.Cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record DeleteCategoryCommand(int id) implements IRequest<Void> {



}
