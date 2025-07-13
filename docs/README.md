# Christmas

## discount policy

- D-day discount
    - date: 2023.12.01 ~ 2023.12.25
    - It will start with a 1,000 won discount and increase by 100 won each day.
- Weekday discount: it will discount 2,023 won by each dessert
- Weekend discount: it will discount 2,023 won by each main menu
- Special discount: Days marked with a star on the calendar offer a 1,000 won discount.
- Present discount: If the total amount exceeds 120,000 won, one bottle of champagne will be offered.

## badge

Badges will be given according to the discount amount.

- over 5,000: star
- over 10,000: tree
- over 20,000: santa

## Caution

- the total purchase amount exceeds 10,000, the event will be offered
- you can not order beverage alone.
- you must order less equal then 20 menu per once.

## Domain

- [x] Make Discounters: i'll implement this using an interface.
    - [x] Weekday Discounter
    - [x] Weekend Discounter
    - [x] Special Discounter
    - [x] Present Discounter

- [ ] Reception
    - [ ] validate domain requirements (e.g, maximum number of menus that can be received, verify valid reserve date)
    - [ ] calculate the purchase amount.
    - [ ] calculate the discount amount.

- [ ] Menu (VO): a value object consists of a menu name and price.

- [ ] Badge-Selector