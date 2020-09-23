package com.paypal.Checkouts;

import com.paypal.*;
import jakarta.json.bind.annotation.JsonbProperty;

public class CaptureRequestType {
    /**
     * Details about the store location where the purchase is being made.
     */
    @JsonbProperty("point_of_sale_details")
    public PointOfSaleDetailsType PointOfSaleDetails;

    /**
     * Details about the business that owns the retail location.
     */
    @JsonbProperty("business_details")
    public BusinessDetailsType BusinessDetails;

    /**
     * Identifies the parties to the transaction.  <strong>Required.</strong>
     */
    @JsonbProperty(value = "credential_info", nillable = true)
    public CredentialInfoType CredentialInfo;

    /**
     * Contains details on the transaction to be performed.  <strong>Required.</strong>
     */
    @JsonbProperty(value = "transaction", nillable = true)
    public TransactionType Transaction;

    /**
     * The set of data to be displayed in the e-receipt.  <strong>Required.</strong>
     */
    @JsonbProperty(value = "receipt_info", nillable = true)
    public ReceiptInfoType ReceiptInfo;

    public static class CredentialInfoType {
        /**
         * Details about the PayPal user who is making the purchase.
         */
        @JsonbProperty("customer")
        public CustomerType Customer;

        /**
         * Details about the retailer that is accepting payment for this purchase.
         */
        @JsonbProperty("retailer")
        public CustomerType Retailer;

        /**
         * Additional verification data for the buyer.
         */
        @JsonbProperty("customer_verification_data")
        public CustomerVerificationDataType CustomerVerificationData;

        /**
         * The billing agreement ID being used for this purchase.
         *
         * Match regex: <code>^B-[2-9A-HJ-NP-Z]{17}$</code>
         */

        public static class CustomerType {
            /**
             * The PayPal payer ID of the user.
             *
             * Match regex: <code>^[2-9A-HJ-NP-Z]{13}$</code>
             */
            @JsonbProperty("account_id")
            public String AccountId;

            /**
             * The value of the QR code that was scanned from the buyer's device.
             *
             * Min length: 1 character
             * Max length: 1000 characters
             */
            @JsonbProperty("qr_code")
            public String QrCode;
        }

        public static class CustomerVerificationDataType {
            /**
             * The customer's name.
             */
            @JsonbProperty("name")
            public PersonNameType Name;

            /**
             * The customer's address.
             */
            @JsonbProperty("address")
            public AddressType Address;

            /**
             * The customer's phone number.
             */
            @JsonbProperty("phone_number")
            public PhoneNumberType PhoneNumber;

            /**
             * The customer's email address.
             *
             * Min length: 3 characters
             * Max length: 254 characters
             */
            @JsonbProperty("email_address")
            public String EmailAddress;
        }

    }

    public static class TransactionType {
        /**
         * Indicates whether the payment is being processed as a partial tender.  If true, PayPal
         * does not validate that the line items provided add up to the total amount.
         */
        @JsonbProperty("partial_tender")
        public Boolean PartialTender;

        /**
         * Indicates whether the merchant will accept an approval for less than the total specified.
         */
        @JsonbProperty("partial_approval_supported")
        public Boolean PartialApprovalSupported;

        /**
         * Details relevant to an advice transaction, e.g. auth advice or forced post.
         */
        @JsonbProperty("advice_info")
        public AdviceInfoType AdviceInfo;

        /**
         * Indicates that the customer should be prompted to enter or modify the tip amount.
         */
        @JsonbProperty("tip_requested")
        public Boolean TipRequested;

        /**
         * Indicates the suggested tip percentages from the caller.
         *
         * Max length: 3 items
         *
         * Match regex: ^((-?\d+)|(-?(\d+)?\.\d+))$
         */
        @JsonbProperty("recommended_tip_percentages")
        public String RecommendedTipPercentages[];

        /**
         * The grand total of the transaction.  <strong>Required.</strong>
         */
        @JsonbProperty("amount")
        public AmountType Amount;

        /**
         * If the transaction was presented to the customer in an another currency, indicates the currency
         * and amount that was presented to the customer. (?)
         */
        @JsonbProperty("amount_original_currency")
        public AmountType AmountOriginalCurrency;

        /**
         * If this purchase is related to a buyer's purchase order (PO), this is the ID of hat PO.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("purchase_order_id")
        public String PurchaseOrderId;

        /**
         * Extra description of the purchase order, if applicable.
         *
         * Max length: 1000 characters
         */
        @JsonbProperty("purchase_order_description")
        public String PurchaseOrderDescription;

        /**
         * Details about the items included in this transaction.
         */
        @JsonbProperty("invoice")
        public InvoiceType Invoice;

        /**
         * Information about delivery of items from this transaction.
         *
         * Max length: 16 items
         */
        @JsonbProperty("delivery_descriptors")
        public DeliveryDescriptorType DeliveryDescriptors[];

        /**
         * If <code>partial_tender</code> is <code>true</code>, it is recommended to populate this
         * structure for best consumer experience.  This will be shown to the customer in their
         * e-Receipt.  If PayPal returned PLCC details and retailer authorized the PLCC on their
         * end, the retailer must pass the authorized PLCC details in this list.  If PayPal
         * returned physical gift card details and retailer charged the physical gift cards on
         * their end, the retailer must pass the charged gift card details in this list.
         *
         * Max length: 32 items
         */
        @JsonbProperty("other_tenders")
        public OtherTenderType OtherTenders[];

        /**
         * Used to list offers presented at the terminal that are not in the PayPal Wallet, e.g.
         * manufacturer coupons.
         *
         * Max length: 100 items
         */
        @JsonbProperty("redeemed_offers")
        public DiscountType RedeemedOffers[];

        /**
         * Indicates some purchased items not given to the buyer at time of sale.
         */
        @JsonbProperty("deferred_fulfillment")
        public Boolean DeferredFulfillment;

        /**
         * An array of name-value pairs to be passed with prior arrangement between PayPal and
         * the merchant/partner.
         *
         * Max length: 20 items
         */
        @JsonbProperty("metadata")
        public MetadataType Metadata[];

        /**
         * Additional information about a transaction.
         */
        @JsonbProperty("transaction_characteristics")
        public TransactionCharacteristicsType TransactionCharacteristics;

        public static class AdviceInfoType {
            /**
             * The source of the authorization.  <strong>Required.</strong>
             */
            @JsonbProperty(value = "authorization_source", nillable = true)
            public AuthorizationSourceType AuthorizationSource;

            /**
             * Indicates whether the indicated authorization source approved the transaction.
             * <strong>Required.</strong>
             */
            @JsonbProperty(value = "authorization_approved", nillable = true)
            public Boolean AuthorizationApproved;

            /**
             * The value of the PayPal-Request-Id HTTP header of the original (failed) call for
             * which standin is being performed.
             *
             * Min length: 1 character
             * Max length: 255 characters
             */
            @JsonbProperty("original_request_idemptoency_key")
            public String OriginalRequestIdempotencyKey;

            /**
             * The approval code provided by the party that approved the transaction.
             *
             * Min length: 1 character
             * Max length: 6 characters
             */
            @JsonbProperty("approval_code")
            public String ApprovalCode;

            /**
             * Reason(s) for declining the authorization request.
             *
             * Max length: 8 items
             */
            @JsonbProperty("decline_reasons")
            public DeclineReasonsType DeclineReasons[];

            /**
             * The amount of the original transaction.  <strong>Required.</strong>
             */
            @JsonbProperty(value = "original_amount", nillable = true)
            public AmountType OriginalAmount;

            /**
             * The portion of the stand-in limit that was used (?)
             */
            @JsonbProperty("limit_amount_used")
            public AmountType LimitAmountUsed;
        }

        public static class InvoiceType {
            /**
             * Retailer-assigned ID for this invoice.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("external_id")
            public String ExternalId;

            /**
             * The merchant's soft descriptor that will appear on the customer's credit card bill.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("soft_descriptor")
            public String SoftDescriptor;

            /**
             * The items in this invoice.
             *
             * Max length: 256 items
             */
            @JsonbProperty("items")
            public ItemType Items[];

            /**
             * Discounts applied at the invoice level.
             *
             * Max length: 32 items
             */
            @JsonbProperty("discounts")
            public DiscountType Discounts[];

            /**
             * The tip(s) or convenience fee(s) included in the total.
             *
             * Max length: 3 items
             */
            @JsonbProperty("tip_convenience_fees")
            public TipConvenienceFeeType TipConvenienceFees[];

            /**
             * The total tax being charged on the transaction.
             */
            @JsonbProperty("total_tax")
            public AmountType TotalTax;

            /**
             * The total delivery cost being charged on the transaction.
             */
            @JsonbProperty("total_delivery_cost")
            public AmountType TotalDeliveryCost;

            /**
             * The total purchase amount.
             */
            @JsonbProperty("total_purchase_amount")
            public AmountType TotalPurchaseAmount;

            public static class ItemType {
                /**
                 * A human readable item name.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("name")
                public String Name;

                /**
                 * Further information about the item.
                 *
                 * Max length: 1000 characters
                 */
                @JsonbProperty("description")
                public String Description;

                /**
                 * The International Article Number or Universal Product Code (UPC) for the item.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("ean")
                public String Ean;

                /**
                 * The Stock-Keeping Unit or other identification code assigned to the item.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("sku")
                public String Sku;

                /**
                 * A retailer could apply different return policies to different items.  Each
                 * return policy would be identified using a label or ID.  This specifies an
                 * item's return policy.  This identifier will be displayed next to the item
                 * in the e-Receipt.
                 *
                 * Max length: 1000 characters
                 */
                @JsonbProperty("return_policy_id")
                public String ReturnPolicyId;

                /**
                 * The total price of the line item (after multiplying the item price by the quantity,
                 * adding applicable tax, and subtracting any applicable discounts).
                 */
                @JsonbProperty("total_price")
                public AmountType TotalPrice;

                /**
                 * The price of a single quantity of the item, not including any discounts or taxes.
                 */
                @JsonbProperty("item_price")
                public AmountType ItemPrice;

                /**
                 * Quantity of the item.  Need not be an integer.
                 *
                 * Max length: 32 characters
                 *
                 * Match regex: ^(\d+|\d*\.\d+)$
                 */
                @JsonbProperty("item_count")
                public String ItemCount;

                /**
                 * Discounts applied to this item.
                 *
                 * Max length: 16 items
                 */
                @JsonbProperty("discounts")
                public DiscountType Discounts[];

                /**
                 * Indicates if this item is taxable.  It not passed, assumed to be <code>true</code>.
                 */
                @JsonbProperty("taxable")
                public Boolean Taxable;

                /**
                 * Indicates if this item is reimbursable.  If not passed, assumed to be <code>true</code>.
                 */
                @JsonbProperty("reimbursable")
                public Boolean Reimbursable;

                /**
                 * The units in which the <code>ItemCount</code> is denominated.
                 */
                @JsonbProperty("item_count_unit")
                public ItemCountUnitType ItemCountUnit;

                /**
                 * The tax percentage, as a fixed-point, signed decimal number.  For instance,
                 * use <code>19.99</code> to indicate a 19.99% tax rate.
                 *
                 * Match regex: ^((-?\d+)|(-?\d*\.\d+))$
                 */
                @JsonbProperty("tax_rate")
                public String TaxRate;

                /**
                 * Fees applied to the item would go here, e.g. California CRV.
                 *
                 * Max length: 16 items
                 */
                @JsonbProperty("additional_fees")
                public AdditionalFeesType AdditionalFees[];

                /**
                 * Manufacturer part number.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("mpn")
                public String Mpn;

                /**
                 * International Standard Book Number.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("isbn")
                public String Isbn;

                /**
                 * Price Look-Up Code.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("plu")
                public String Plu;

                /**
                 * Model number.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("model_number")
                public String ModelNumber;

                /**
                 * Style number.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("style_number")
                public String StyleNumber;

                /**
                 * Identifies whether this item is eligible for discounts, promotions, etc.  If
                 * not passed, assumed to be <code>true</code>.
                 */
                @JsonbProperty("eligible_for_promotion")
                public Boolean EligibleForPromotion;

                public static class AdditionalFeesType {
                    /**
                     * Name of fee, assigned by retailer.  <strong>Required.</strong>
                     *
                     * Max length: 255 characters
                     */
                    @JsonbProperty(value = "name", nillable = true)
                    public String Name;

                    /**
                     * The amount of the additional fee.  <strong>Required.</strong>
                     */
                    @JsonbProperty(value = "amount", nillable = true)
                    public AmountType Amount;
                }
            }
        }

        public static class DeliveryDescriptorType {
            /**
             * For delivery to a store for customer pick-up, this is the retailer-assigned store ID,
             * set up ahead of time with PayPal.  Merchants can pass either the store ID or the full
             * address.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("store_external_id")
            public String StoreExternalId;

            /**
             * For delivery to a store for customer pick-up, this is the address of the store where
             * the items will be picked up by the customer.
             */
            @JsonbProperty("address")
            public AddressType Address;

            /**
             * Name of customer or other entity to whom delivery is directed.  For store pick-up,
             * name of store if StoreExternalId is not provided.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("name")
            public String Name;

            /**
             * The items to be included in this delivery.
             *
             * Max length: 256 items
             */
            @JsonbProperty("items")
            public ItemType Items[];

            public static class ItemType {
                /**
                 * The Stock-Keeping Unit or other identification code assigned to the item.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("sku")
                public String Sku;

                /**
                 * The International Article Number of Universal Product Code (UPC) for the item.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("ean")
                public String Ean;

                /**
                 * The International Standard Book Number for the item.
                 *
                 * Max length: 256 characters
                 */
                @JsonbProperty("isbn")
                public String Isbn;

                /**
                 * Quantity of the item included in this delivery.  Need not be an integer.
                 *
                 * Max length: 32 characters
                 *
                 * Match regex: ^(\d+|\d*\.\d+)$
                 */
                @JsonbProperty("item_count")
                public String ItemCount;

                /**
                 * The units in which the <code>ItemCount</code> property is denominated.
                 */
                @JsonbProperty("item_count_unit")
                public ItemCountUnitType ItemCountUnit;
            }
        }

        public static class OtherTenderType {
            /**
             * Name of the tender that can be shown on the e-Receipt.  <strong>Required.</strong>
             *
             * Max length: 256 characters
             */
            @JsonbProperty(value = "name", nillable = true)
            public String Name;

            /**
             * The amount of the other tender.  <strong>Required.</strong>
             */
            @JsonbProperty(value = "amount", nillable = true)
            public AmountType Amount;
        }

        public static class DiscountType {
            /**
             * A human readable discount name.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("name")
            public String Name;

            /**
             * Further information about the discount.
             *
             * Max length: 1000 characters
             */
            @JsonbProperty("description")
            public String Description;

            /**
             * The amount of the discount.
             */
            @JsonbProperty("amount")
            public AmountType Amount;

            /**
             * The type of discount or offer.
             */
            @JsonbProperty("redeemed_offer_type")
            public RedeemedOfferTypeType RedeemedOfferType;

            /**
             * ID of the redeemed discount in PayPal's incentive system.  May be assigned
             * by PayPal or external discount provider.
             *
             * Max length: 256 characters
             */
            @JsonbProperty("redeemed_offer_id")
            public String RedeemedOfferId;

            /**
             * Loyalty points accrued.
             *
             * Max length: 32 characters
             *
             * Match regex: ^(\d+|\d*\.\d+)$
             */
            @JsonbProperty("points_accrued")
            public String PointsAccrued;
        }

        public static class TransactionCharacteristicsType {
            /**
             * The type of recurring payment.
             */
            @JsonbProperty("recurring_payment_type")
            public RecurringPaymentTypeType RecurringPaymentType;

            /**
             * Specifies if the authorization amount is final or an estimate.
             */
            @JsonbProperty("auth_amount_estimated")
            public Boolean AuthAmountEstimated;

            /**
             * Indicates if the transaction is used to fund a stored value instrument
             * (prepaid card).  <strong>Required.</strong>
             */
            @JsonbProperty(value = "stored_value_purchase", nillable = true)
            public Boolean StoredValuePurchase;

            /**
             * If the transaction is being used to fund a stored value instrument,
             * used to indicate the amount being funded to the stored value instrument.
             */
            @JsonbProperty("stored_value_amount")
            public AmountType StoredValueAmount;

            /**
             * Details pertaining to transaction's security status.
             */
            @JsonbProperty("transaction_security_indicator_type")
            public TransactionSecurityIndicatorTypeType TransactionSecurityIndicatorType;
        }

    }

    public static class ReceiptInfoType {
        /**
         * Merchant-assigned identifier of the cashier, if any, who performed this transaction.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("cashier_id")
        public String CashierId;

        /**
         * Name of the cashier, if any, who performed this transaction.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("cashier_name")
        public String CashierName;

        /**
         * Merchant-assigned identifier of the store manager, if any, of the store where this
         * transaction occurred.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("store_manager_id")
        public String StoreManagerId;

        /**
         * Name of the store manager, if any, of the store where this transaction occurred.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("store_manager_name")
        public String StoreManagerName;

        /**
         * The type of receipt template.
         */
        @JsonbProperty("receipt_template")
        public ReceiptTemplateType ReceiptTemplate;

        /**
         * Identifier for the receipt.  This renders the barcode if <code>barcode_type</code>
         * is defined.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("barcode")
        public String Barcode;

        /**
         * The format to render the barcode.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("barcode_type")
        public String BarcodeType;

        /**
         * The date and time, in <a href="https://tools.ietf.org/html/rfc3339#section-5.6">Internet
         * date and time format</a>, of when the transaction was initiated at the sales terminal.
         * Seconds are required while fractional seconds are optional.
         *
         * For example, Monday, September 21st, 2020, at 2:38:50AM Central Daylight Time would be
         * specified as <code>2020-09-21T02:38:50-05:00</code>.
         */
        @JsonbProperty("terminal_sales_time")
        public String TerminalSalesTime;

        /**
         * The time zone, in <a href="https://www.iana.org/time-zones">Internet Assigned Numbers
         * Authority (IANA) time zone (tz) database format</a> (for example, <code>America/New_York</code>
         * or <code>Etc/UTC</code>), of the time zone where the sales terminal is located.
         *
         * Max length: 127 characters
         */
        @JsonbProperty("terminal_sales_time_zone")
        public String TerminalSalesTimeZone;

        /**
         * Transaction ID of the merchant.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("retailer_transaction_id")
        public String RetailerTransactionId;

        /**
         * PayPal displays this tring on the e-receipt.  You can use this string to display marketing
         * information to the consumer.  PayPal recommends including an expiration date on these
         * marketing messages.
         *
         * Max length: 1000 characters
         */
        @JsonbProperty("retailer_receipt_data")
        public String RetailerReceiptData;

        /**
         * PayPal displays this string on the e-receipt.  You can use this string to display the
         * return policy to the consumer.
         *
         * Max length: 1000 characters
         */
        @JsonbProperty("return_policy_message")
        public String ReturnPolicyMessage;

        /**
         * PayPal displays this string on the e-receipt.  You can use this number for reference.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("receipt_reference_number")
        public String ReceiptReferenceNumber;
    }

}
