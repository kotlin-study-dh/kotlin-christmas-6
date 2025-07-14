

# Christmas sale
- period : 2023.12.1 ~ 2023.12.25
- Christmas D-day discount : 12/1 -> 1000 won discount, 12/2 -> 1100 won discount ... 12/25 -> 3400 won discount
- weekday(Sunday ~ Thursday) : discount 2023 won for dessert
- weekend(Friday, Saturday) : discount 2023 won for main menu
- special discount : (3, 10, 17, 24, 25, 31) date -> 1000won discount
- gift event : before discount, total price is 120000 or more -> free 1 Champagne
- Except Christmas D-day discount, all events is provided during 2023.12.1 ~ 2023.12.31

# Badge
base on the discount amount,
5000 or more: Star
10000 or more: Tree
20000 or more: Santa

# Notes
- events applies when total order value of 10000 won or more
- only order drinks, cannot order
- can order up to 20 menu at a time


# input
- 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
  - only between 1~31
  - if wrong, show [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
- 주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  - not exist menu -> [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - menu count is only number 1 or more -> if not [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - invalid menu format -> [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - duplicate menu -> [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
- error message must start with [ERROR]

# Output
- Feel free to print the order of the order menu.
- total benefit price = benefit price + gift menu
- estimated payment price = total order price before discount - discount price
- if it isn't gift event, print 없음
- benefit list
  - show only applied event list for user
  - if not -> print 없음
  - print events any order 
- badge : if not -> 없음


## Menu
```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```