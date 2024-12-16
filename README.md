# Description
This is a Java implementation of Receipt Processor API as defined by [the following challenge](https://github.com/fetch-rewards/receipt-processor-challenge).

I have a C++ background and would love to get more experience with Go, however the API was written using Java to reduce development time. Dockerized setup instructions will be provided soon.

# Features
1. **Loose Coupling**. API models are separated from back-end entities isolating front-end changes from storage and point calculation.
2. **Exact Math**. Floating point arithmetic is not suitable for financial operations which is why this project uses arbitrary-precision signed decimal numbers.
3. **Input Validation**. User input is thoroughly validated to ensure adherence to specifications.
4. **Reusable Rules**. Point rule classes were written to be reusable and configurable.
5. **Pipeline Pattern**. Point rules are independent pipes which are aggregated into a point pipeline which allows rules to be easily swapped in and out. This approach also allows to test rules separately from each other.
6. **Unit Tests**. Unit tests cover input model validation and point rules to ensure adherence to specifications. Note that the endpoints themselves were only manually tested.

# Notes
- Receipt purchase time technically does not adhere to [proper OpenAPI time format specifications](https://spec.openapis.org/registry/format/time). It should be a time with a timezone offset. However, the challenge does not mention timezones anywhere and never shows purchase time seconds, so I decided to use local time and restrict it to hh:mm format, e.g. "12:13".