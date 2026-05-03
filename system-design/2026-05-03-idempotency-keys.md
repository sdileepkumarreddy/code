# Idempotency Keys for Safe Retries

## Problem
Clients retry requests because of timeouts, dropped connections, or transient server failures. Without protection, the same logical operation can be executed multiple times, which is dangerous for actions such as payment capture, order creation, or job submission.

## Core idea
The client sends a unique idempotency key with each create-style request. The server stores the first result associated with that key and returns the same outcome for duplicate retries instead of executing the operation again.

## Practical design
- **Scope**: tie the key to a caller identity such as account ID, tenant, or API key.
- **Request fingerprint**: store a hash of the payload so the same key cannot be reused for a different request body.
- **Persistence**:
  - Small systems: relational table keyed by `(caller, idempotency_key)`.
  - High-throughput APIs: Redis for fast lookup plus durable write of the business result.
- **Response replay**: persist status code and response body or a reference to the created resource.
- **TTL**: expire keys after a practical retry window such as 24 hours.

## Failure handling
- **Before commit**: safe to retry because no successful result was stored.
- **After commit but before response reaches client**: duplicate request returns the stored result instead of creating another resource.
- **Payload mismatch**: reject with a client error because the key is being reused incorrectly.

## Trade-offs
- **Pros**: prevents duplicate side effects, makes retries safe, simplifies client behavior.
- **Cons**: needs storage, retention policy, and careful handling of partial failures.
- **Design choice**: stronger durability reduces duplicate risk, but it increases write latency and storage cost.

## Real-world examples
- Payment APIs use idempotency keys for charge creation.
- Job schedulers use request IDs to avoid enqueueing the same work twice.
- Internal platform APIs use them when provisioning resources that clients may retry under network instability.
