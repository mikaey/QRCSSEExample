package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class HATEOASLinkType {
    /**
     * The complete target URL.  To make the related call, combine the method with this
     * <a href="https://tools.ietf.org/html/rfc6570">URI Template-formatted</a> link.
     * For pre-processing, include the <code>$</code>, <code>(</code>, and <code>)</code>
     * characters.  The <code>href</code> is the key HATEOAS component that links a
     * completed call with a subsequent call.
     */
    @JsonbProperty("href")
    public String Href;

    /**
     * The <a href="https://tools.ietf.org/html/rfc5988#section-4">link relation type</a>,
     * which serves as an ID for a link that unambiguously describes the semantics of the
     * link.  See <a href="ttps://www.iana.org/assignments/link-relations/link-relations.xhtml">Link
     * Relations</a>.
     */
    @JsonbProperty("rel")
    public String Rel;

    /**
     * The HTTP method required to make the related call.
     */
    @JsonbProperty("method")
    public HttpMethodType Method;
}
