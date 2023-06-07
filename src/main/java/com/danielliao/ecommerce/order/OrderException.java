package com.danielliao.ecommerce.order;

class OrderException extends Exception
{
      public OrderException() {}

      public OrderException(String message) {
         super(message);
      }
 }