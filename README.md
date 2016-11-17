# AAA
[![Build Status](https://travis-ci.org/spacecrio/AAA.svg?branch=master)](https://travis-ci.org/spacecrio/AAA)

 **Authentication, Authorization, Accounting** 
 Цель проекта - написания программы на Java,
 которая бы реализовывала аутентификацию клиента по логину и поролю,
 и после, в зависимости от его внустренних данных,
 предоставляла бы ограниченный и отслеживаемый доступ к некоторому условному ресурсу.
 
 ### Build
 ```
 $ ./build.sh
 ```
 ### Run
 ```
 $ ./run.sh <args>
 ```
 ### Test 
 ```
 $ ./test.sh
 ```
 -
 ### Help
 ```
 Allowed:
  -login -pass 
  -login -pass -res -role 
  -login -pass -res -role -ds -de -val
 
  -h,--help                show help.
  -login,--login <arg>     Your login.
  -pass,--password <arg>   Your password.
  -res,--resource <arg>    Requested resource.
  -role,--role <arg>       Intention on this resource.
  -val,--value <arg>       Value of resource
  -de,--dateend <arg>      Using end date. [DD-MM-YYYY]
  -ds,--datestart <arg>    Using start date. [DD-MM-YYYY]
  ```
