/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmail.com)
 */
package io.jenetics.prog.op;

import static java.util.Objects.requireNonNull;
import static io.jenetics.internal.util.Hashes.hash;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an operation which always returns the same, constant, value. To
 * improve readability, constants may have a name. If a name is given, this name
 * is used when printing the program tree. The {@code Const} operation is a
 * <em>terminal</em> operation.
 *
 * <pre>{@code
 * final static Op<Double> PI = Const.of("π", Math.PI);
 * final static Op<Double> ONE = Const.of(1.0);
 * }</pre>
 *
 * @author <a href="mailto:franz.wilhelmstoetter@gmail.com">Franz Wilhelmstötter</a>
 * @version 4.1
 * @since 3.9
 */
public final class Const<T> implements Op<T>, Serializable {

	private static final long serialVersionUID = 1L;

	private final String _name;
	private final T _const;

	private Const(final String name, final T constant) {
		_name = name;
		_const = constant;
	}

	@Override
	public T apply(final T[] value) {
		return _const;
	}

	/**
	 * Return the constant value.
	 *
	 * @since 4.1
	 *
	 * @return the constant value
	 */
	public T value() {
		return _const;
	}

	@Override
	public String name() {
		return _name;
	}

	@Override
	public int arity() {
		return 0;
	}

	@Override
	public int hashCode() {
		return hash(_name, hash(_const));
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this ||
			obj instanceof Const &&
			Objects.equals(((Const)obj)._name, _name) &&
			equal(((Const)obj)._const, _const);
	}

	private static boolean equal(final Object a, final Object b) {
		if (a instanceof Double && b instanceof Double) {
			return ((Double)a).doubleValue() == ((Double)b).doubleValue();
		} else if (a instanceof Float && b instanceof Float) {
			return ((Float)a).floatValue() == ((Float)b).floatValue();
		}

		return Objects.equals(a, b);
	}

	@Override
	public String toString() {
		return _name != null ? _name : Objects.toString(_const);
	}

	/**
	 * Return a new constant with the given name and value.
	 *
	 * @param name the constant name
	 * @param value the constant value
	 * @param <T> the constant type
	 * @return a new constant
	 * @throws NullPointerException if the given constant {@code name} is
	 *        {@code null}
	 */
	public static <T> Const<T> of(final String name, final T value) {
		return new Const<>(requireNonNull(name), value);
	}

	/**
	 * Return a new constant with the given value.
	 *
	 * @param value the constant value
	 * @param <T> the constant type
	 * @return a new constant
	 */
	public static <T> Const<T> of(final T value) {
		return new Const<>(null, value);
	}

}
