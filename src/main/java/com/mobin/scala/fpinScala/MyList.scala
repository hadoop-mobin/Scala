package com.mobin.scala.fpinScala

/**
  * Created by Mobin on 2016/6/13.
  */
sealed  trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends  List[A]
object MyList {
  //求和
  def sum(ints: List[Int]): Int = ints match {
        case  Nil => 0
        case Cons(x,xs) => x + sum(xs)
      }

  //求积
  def product(ds: List[Int]): Int = ds match {
    case Nil => 1
    case Cons(0,_) => 0
    case Cons(x,y) => x * product(y)
  }

  //在伴生对象中定义一个可变参数的apply方法以便构造这个数据类型的实例是一种惯例
  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  //"删除第一个元素"
  def tail[A](l: List[A]) : List[A]={
       l match {
         case Nil => Nil
         case Cons(x,y) => y
       }
  }

  //替换第一个元素
  def setHeader[A](first : A,list: List[A]) : List[A] = {
    list match {
        case Nil => Nil
        case Cons(x,y) => Cons(first,y)
      }
  }

  //删除前n个元素
  def drop[A](l: List[A], n: Int) : List[A] = {
        if (n == 0) l
        else drop(tail(l),n-1)
  }


  //删除列表中前缀全部符合判定的元素
//  def dropWhile(l: List[String], f: String => Boolean,n: Int) : List[String] = {
//             l match {
//               case Cons(x, y) if f(x) => dropWhile(y, f)
//               case Cons(x, _)=>  dropWhile(l,f)
//             }
//  }


  def prefix(x: String) : Boolean= {
    println(x)
     if(x.startsWith("A")) true
    else false
  }


  def main(args: Array[String]) {
    //有了apply方法就可以使用MyList(1,2,3,4,5)的方式来创建列表
    //MyList(1,2,3,4,5)等于 Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    val x = MyList(1,2,3,4,5) match {
      case Cons(x,Cons(2,Cons(4, _))) => x
      case Nil => 42
      case Cons(x,Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h ,t) => h + sum(t)
      case  _ => 101
    }
    println(x)
    val list = MyList(1,2,13,4,5,6)
    println("sum:  " + sum(list))
    println("product:   " + product(list))
    println("删除第一个元素： " + tail(list))
    println("替换第一个元素"+setHeader(7, list))
    println("删除前n个元素"+ drop(list, 3))

    val list1 = MyList("AA","MOBIN","AMOBIN","MOB")
   // println("删除符合判定的元素：" + dropWhile(list1, prefix))

  }
}
